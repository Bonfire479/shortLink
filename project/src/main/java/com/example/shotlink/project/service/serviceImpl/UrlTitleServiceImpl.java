package com.example.shotlink.project.service.serviceImpl;


import com.example.shotlink.project.service.UrlTitleService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UrlTitleServiceImpl implements UrlTitleService {
    @Override
    public String getTitleByUrl(String url) {
        try {
            // 使用Jsoup连接到URL并获取文档
            Document doc = Jsoup.connect(url).get();

            // 获取文档的标题元素
            Element titleElement = doc.select("title").first();

            // 如果标题元素不为空，则返回标题文本
            if (titleElement != null) {
                return titleElement.text();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 如果无法获取标题，则返回空字符串
        return "无法获取网站标题";
    }
}
