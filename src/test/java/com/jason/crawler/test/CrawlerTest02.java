package com.jason.crawler.test;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import com.jason.crawler.Crawler;
import com.jason.crawler.parser.PageParser;
import com.jason.crawler.conf.CrawlerConf;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 爬虫示例02：爬取页面，下载Html文件
 *
 * @author jason 2017-10-09 19:48:48
 */
public class CrawlerTest02 {

    public static void main(String[] args) {

        Crawler crawler = new Crawler.Builder()
                .setUrls("https://gitee.com/xuxueli0323/projects?page=1")
                .setWhiteUrlRegexs("https://gitee\\.com/xuxueli0323/projects\\?page=\\d+")
                .setThreadCount(3)
                .setPageParser(new PageParser<Object>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, Object pageVo) {

                        // 文件信息
                        String htmlData = html.html();
                        String filePath = "/Users/kmg/Downloads/tmp";
                        String fileName = com.jason.crawler.util.FileUtil.getFileNameByUrl(html.baseUri(), CrawlerConf.CONTENT_TYPE_HTML);

                        // 下载Html文件
                        HttpUtil.downloadFile(fileName,FileUtil.file(filePath));
                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
