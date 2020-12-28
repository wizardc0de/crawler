package com.jason.crawler.test;

import com.jason.crawler.Crawler;
import com.jason.crawler.parser.strategy.NonPageParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 爬虫示例09：采集非Web页面，如JSON接口等，直接输出响应数据
 *
 * @author jason 2018-10-17
 */
public class CrawlerTest09 {
    private static Logger logger = LoggerFactory.getLogger(CrawlerTest05.class);

    public static void main(String[] args) {

        // 构造爬虫
        Crawler.Builder builder = new Crawler.Builder();
        builder.setUrls("http://news.baidu.com/widget?id=LocalNews&ajax=json");
        builder.setPageParser(new NonPageParser() {
            @Override
            public void parse(String url, String pageSource) {
                System.out.println(url + ": " + pageSource);
            }
        });
        Crawler crawler = builder
                .build();

        // 启动
        crawler.start(true);

    }

}
