package com.example.shortlink.admin.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.shortlink.admin.common.biz.user.UserContext;
import com.example.shortlink.admin.common.biz.user.UserInfoDTO;
import com.example.shortlink.admin.common.constants.RedisKeyConstant;
import com.example.shortlink.admin.common.convention.exception.ClientException;
import com.example.shortlink.admin.common.convention.exception.ServiceException;
import com.example.shortlink.admin.common.enums.BaseErrorCode;
import com.example.shortlink.admin.dao.entity.UserDO;
import com.example.shortlink.admin.dao.mapper.UserMapper;
import com.example.shortlink.admin.dto.req.GroupSaveReqDTO;
import com.example.shortlink.admin.dto.req.UserLoginReqDTO;
import com.example.shortlink.admin.dto.req.UserRegisterReqDTO;
import com.example.shortlink.admin.dto.req.UserUpdateReqDTO;
import com.example.shortlink.admin.dto.resp.UserLoginRespDTO;
import com.example.shortlink.admin.dto.resp.UserRespDTO;
import com.example.shortlink.admin.service.GroupService;
import com.example.shortlink.admin.service.UserService;
import com.example.shortlink.admin.util.BeanUtil;
import com.example.shortlink.admin.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RBloomFilter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, UserDO> implements UserService {
    private final RBloomFilter<String> userRegisterBloomFilter;
    private final RedissonClient redissonClient;
    private final StringRedisTemplate stringRedisTemplate;
    private final GroupService groupService;
    @Override
    public UserRespDTO getUserByUsername(String username) {
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getUsername, username);
        UserDO user = getOne(queryWrapper);
        if (user == null)
            throw new ClientException("用户记录不存在", BaseErrorCode.CLIENT_ERROR);
        UserRespDTO userRespDTO = new UserRespDTO();
        BeanUtil.convert(user, userRespDTO);
        return userRespDTO;
    }

    @Override
    public Boolean hasUsername(String username) {
        return userRegisterBloomFilter.contains(username);
    }

    @Override
    public void register(UserRegisterReqDTO param) {
        if (hasUsername(param.getUsername())) {
            throw new ClientException("用户名已被注册");
        }

        RLock lock = redissonClient.getLock(RedisKeyConstant.LOCK_USER_REGISTER + param.getUsername());
        boolean lockSuccess = lock.tryLock();
        if (!lockSuccess) {
            throw new ClientException("用户名正在被其他人注册");
        }
        try {
            int insert = baseMapper.insert(BeanUtil.convert(param, UserDO.class));
            if (insert < 1) {
                throw new ServiceException("用户名注册失败");
            }
            userRegisterBloomFilter.add(param.getUsername());
            groupService.addGroup(param.getUsername(),new GroupSaveReqDTO("默认分组"));

        } finally {
            lock.unlock();
        }
    }

    @Override
    public void updateUser(UserUpdateReqDTO param) {
        if (!Objects.equals(param.getUsername(), UserContext.getUsername()))
            throw new ClientException("用户非法更新其他用户信息");
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(UserDO::getUsername, param.getUsername());
        baseMapper.update(BeanUtil.convert(param, UserDO.class), queryWrapper);
        return;
    }

    @Override
    public UserLoginRespDTO login(UserLoginReqDTO param) {
        Boolean loggedIn = stringRedisTemplate.hasKey(RedisKeyConstant.USER_LOGIN_TOKEN + param.getUsername());
        if (loggedIn != null && loggedIn.equals(Boolean.TRUE)){
            throw new ClientException("用户重复登陆");
        }
        LambdaQueryWrapper<UserDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper
                .eq(UserDO::getUsername, param.getUsername())
                .eq(UserDO::getPassword, param.getPassword());
        UserDO user = getOne(queryWrapper);
        if (user == null){
            throw new ClientException("用户名或密码错误");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getUsername());
        map.put("realName", user.getRealName());
        String token = JWTUtil.generateToken(map);

        stringRedisTemplate.opsForValue().set(
                RedisKeyConstant.USER_LOGIN_TOKEN + param.getUsername(),
                token,
                12,
                TimeUnit.HOURS
                );

        return new UserLoginRespDTO(token);
    }

    @Override
    public Boolean checkLogin(String username) {
        return stringRedisTemplate.hasKey(RedisKeyConstant.USER_LOGIN_TOKEN + username);
    }

    @Override
    public Boolean logout(String username) {
        if (!Objects.equals(UserContext.getUsername(), username)){
            throw new ClientException("退出登录用户与当前用户不一致");
        }
        return stringRedisTemplate.delete(RedisKeyConstant.USER_LOGIN_TOKEN + username);
    }


}
