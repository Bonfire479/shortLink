package com.example.shotlink.project.service.serviceImpl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shotlink.project.common.convention.exception.ClientException;
import com.example.shotlink.project.common.convention.exception.ServiceException;
import com.example.shotlink.project.common.enums.ValidDateTypeEnum;
import com.example.shotlink.project.dao.entity.ShortLinkGotoDO;
import com.example.shotlink.project.dao.entity.ShortLinkDO;
import com.example.shotlink.project.dao.mapper.ShortLinkGotoMapper;
import com.example.shotlink.project.dao.mapper.ShortLinkMapper;
import com.example.shotlink.project.dto.req.*;
import com.example.shotlink.project.dto.resp.ShortLinkCreateBatchRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkCreateRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkGroupCountRespDTO;
import com.example.shotlink.project.dto.resp.ShortLinkPageRespDTO;
import com.example.shotlink.project.service.ShortLinkService;
import com.example.shotlink.project.util.BeanUtil;
import com.example.shotlink.project.util.HashUtil;
import com.example.shotlink.project.util.ShortLinkUtil;
import io.lettuce.core.RedisClient;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.shotlink.project.common.constants.RedisKeyConstant.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Service
@Slf4j
@RequiredArgsConstructor
public class ShortLinkServiceImpl extends ServiceImpl<ShortLinkMapper, ShortLinkDO> implements ShortLinkService {
    private final RBloomFilter<String> shortLinkBloomFilter;
    private final ShortLinkGotoMapper shortLinkGotoMapper;
    private final StringRedisTemplate stringRedisTemplate;
    private final RedissonClient redissonClient;


    @Transactional
    @Override
    public ShortLinkCreateRespDTO createShortLink(ShortLinkCreateReqDTO param) {
        String shortLink, fullShortLinkUrl;
        int count = 0;
        do {
            //TODO 这个hashUtil是怎么做的
            shortLink = HashUtil.hashToBase62(param.getOriginUrl() + System.currentTimeMillis());
            fullShortLinkUrl = param.getDomain() + "/" + shortLink;
            count++;
            if (count > 10)
                throw new ServiceException("短链接超出生成最大重试次数，生成失败");
        } while (shortLinkBloomFilter.contains(fullShortLinkUrl));

        ShortLinkDO shortLinkDO = BeanUtil.convert(param, ShortLinkDO.class);
        shortLinkDO.setShortUri(shortLink);
        shortLinkDO.setFullShortUrl(param.getDomain() + "/" + shortLink);
        shortLinkDO.setEnableStatus(0);
        shortLinkDO.setFavicon(getUrlFavicon(param.getOriginUrl()));

        ShortLinkGotoDO shortLinkGotoDO = ShortLinkGotoDO.builder()
                .fullShortUrl(fullShortLinkUrl)
                .gid(param.getGid())
                .build();

        try {
            baseMapper.insert(shortLinkDO);
            shortLinkGotoMapper.insert(shortLinkGotoDO);
            shortLinkBloomFilter.add(fullShortLinkUrl);
            //缓存预热 根据有效期类型设置不同的过期时间
            stringRedisTemplate.opsForValue().set(
                    SHORT_LINK_KEY + fullShortLinkUrl,
                    param.getOriginUrl(),
                    ShortLinkUtil.getCacheValidDate(param.getValidDate()),
                    TimeUnit.MILLISECONDS);
            ShortLinkCreateRespDTO respDTO = BeanUtil.convert(shortLinkDO, ShortLinkCreateRespDTO.class);
            respDTO.setFullShortUrl("http://" + respDTO.getFullShortUrl());
            return respDTO;

        } catch (DuplicateKeyException ex) {
            log.error("[短链接记录插入失败]\n fullShortUrl : [{}]\n 请求参数: [{}]", fullShortLinkUrl, param);
            shortLinkBloomFilter.add(fullShortLinkUrl);
            throw new ServiceException("短链接记录插入失败");
        }
    }

    @Override
    public ShortLinkCreateBatchRespDTO createShortLinkBatch(ShortLinkCreateBatchReqDTO param) {

        return null;
    }

    @Override
    public IPage<ShortLinkPageRespDTO> pageShortLink(ShortLinkPageReqDTO param) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, param.getGid())
                .eq(ShortLinkDO::getEnableStatus, 0)
                .orderByDesc(ShortLinkDO::getCreateTime);

        IPage<ShortLinkDO> page = baseMapper.selectPage(param, queryWrapper);
        return page.convert(each -> {
            ShortLinkPageRespDTO converted = BeanUtil.convert(each, ShortLinkPageRespDTO.class);
            converted.setFullShortUrl("http://" + converted.getFullShortUrl());
            return converted;
        });
    }

    @Override
    public List<ShortLinkGroupCountRespDTO> groupCountShortLink(List<String> gidList) {
        QueryWrapper<ShortLinkDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("gid, count(*) as shortLinkCount")
                .eq("enable_status", 0)
                .in("gid", gidList)
                .groupBy("gid");

        List<Map<String, Object>> maps = baseMapper.selectMaps(queryWrapper);
        return BeanUtil.convert(maps, ShortLinkGroupCountRespDTO.class);
    }

    @Override
    public void updateShortLink(UpdateShortLinkReqDTO param) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, param.getGid())
                .eq(ShortLinkDO::getFullShortUrl, param.getFullShortUrl());
        if (!baseMapper.exists(queryWrapper)) {
            throw new ClientException("原短链接不存在!");
        }

        ShortLinkDO shortLinkDO = BeanUtil.convert(param, ShortLinkDO.class);
        if (param.getValidDateType().equals(ValidDateTypeEnum.PERMANENT)) {
            shortLinkDO.setValidDate(null);
        }
        baseMapper.update(shortLinkDO, queryWrapper);
    }

    @Transactional
    @Override
    public Boolean changeShortLinkGroup(ShortLinkChangeGroupReqDTO param) {
        LambdaQueryWrapper<ShortLinkDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                .eq(ShortLinkDO::getGid, param.getOldGid())
                .eq(ShortLinkDO::getFullShortUrl, param.getFullShortUrl());

        ShortLinkDO shortLinkDO = baseMapper.selectOne(queryWrapper);
        if (shortLinkDO == null) {
            throw new ClientException("原链接记录不存在");
        }

        baseMapper.delete(queryWrapper);

        shortLinkDO.setGid(param.getNewGid());
        baseMapper.insert(shortLinkDO);
        return true;
    }

    @SneakyThrows
    @Override
    public void restoreUrl(String shortUrl, ServletRequest request, ServletResponse response) {
        String fullShortUrl = request.getServerName() + "/" + shortUrl;
        //防止缓存穿透，布隆过滤器里没有短链接就肯定没有
        if (!shortLinkBloomFilter.contains(fullShortUrl)) {
            ((HttpServletResponse) response).sendRedirect("/page/notfound");
            return;
        }

        //布隆过滤器里有，检查一下是不是空值、过期、在回收站的短链接
        String isNull = stringRedisTemplate.opsForValue().get(SHORT_LINK_IS_NULL_KEY + fullShortUrl);
        if (StrUtil.isNotBlank(isNull)){
            ((HttpServletResponse) response).sendRedirect("/page/notfound");
            return;
        }

        //redis存在短链接
        String originUrl = stringRedisTemplate.opsForValue().get(SHORT_LINK_KEY + fullShortUrl);
        if (StrUtil.isNotBlank(originUrl)) {
            ((HttpServletResponse) response).sendRedirect(originUrl);
            return;
        }

        //redis不存在短链接
        RLock lock = redissonClient.getLock(LOCK_SHORT_LINK_KEY + fullShortUrl);
        lock.lock();
        try {
            //双重检查
            originUrl = stringRedisTemplate.opsForValue().get(SHORT_LINK_KEY + fullShortUrl);
            if (StrUtil.isNotBlank(originUrl)) {
                ((HttpServletResponse) response).sendRedirect(originUrl);
                return;
            }

            //根据fullShortUrl拿到路由表的对应gid
            LambdaQueryWrapper<ShortLinkGotoDO> queryWrapper = Wrappers.lambdaQuery(ShortLinkGotoDO.class)
                    .eq(ShortLinkGotoDO::getFullShortUrl, fullShortUrl);
            ShortLinkGotoDO shortLinkGotoDO = shortLinkGotoMapper.selectOne(queryWrapper);
            if (shortLinkGotoDO == null) {//布隆过滤器误判有短链接，所以这里要缓存空值，防止下次再打到数据库
                stringRedisTemplate.opsForValue().set(SHORT_LINK_IS_NULL_KEY + fullShortUrl, "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect("/page/notfound");
                return;
            }

            //根据gid和fullShortUrl拿到shortLink实体
            String gid = shortLinkGotoDO.getGid();
            LambdaQueryWrapper<ShortLinkDO> shortLinkDOQueryWrapper = Wrappers.lambdaQuery(ShortLinkDO.class)
                    .eq(ShortLinkDO::getGid, gid)
                    .eq(ShortLinkDO::getFullShortUrl, fullShortUrl)
                    .eq(ShortLinkDO::getEnableStatus, 0);
            ShortLinkDO shortLinkDO = baseMapper.selectOne(shortLinkDOQueryWrapper);
            if (shortLinkDO == null) {//布隆过滤器误判有短链接，所以这里要缓存空值，防止下次再打到数据库，为空也可能是enableStatus为1，即短链接在回收站里
                stringRedisTemplate.opsForValue().set(SHORT_LINK_IS_NULL_KEY + fullShortUrl, "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect("/page/notfound");
                return;
            }

            //判断短链接是否过期
            if (shortLinkDO.getValidDate() != null && shortLinkDO.getValidDate().after(new Date())){
                stringRedisTemplate.opsForValue().set(SHORT_LINK_IS_NULL_KEY + fullShortUrl, "-", 30, TimeUnit.MINUTES);
                ((HttpServletResponse) response).sendRedirect("/page/notfound");
            }

            //把originUrl存到redis 并且重定向
            stringRedisTemplate.opsForValue().set(
                    SHORT_LINK_KEY + fullShortUrl,
                    shortLinkDO.getOriginUrl(),
                    ShortLinkUtil.getCacheValidDate(shortLinkDO.getValidDate()),
                    TimeUnit.MILLISECONDS);

            ((HttpServletResponse) response).sendRedirect(shortLinkDO.getOriginUrl());
            return;
        } finally {
            lock.unlock();
        }
    }


    public static String getUrlFavicon(String url) {
        try {
            // 使用Jsoup连接到URL并获取文档
            Document doc = Jsoup.connect(url).get();

            // 获取所有的<link>标签
            Elements linkElements = doc.select("link");

            // 遍历<link>标签，寻找包含图标的标签
            for (Element linkElement : linkElements) {
                String rel = linkElement.attr("rel");
                if (rel.contains("icon") || rel.contains("shortcut")) {
                    // 获取图标的链接
                    String href = linkElement.attr("href");
                    // 可能会有相对路径，需要转换为绝对路径
                    if (!href.startsWith("http")) {
                        href = url + href;
                    }
                    return href;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果无法获取图标链接，则返回空字符串
        return "";
    }

}
