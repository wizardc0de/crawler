package com.jason.crawler.test;

import cn.hutool.http.HttpUtil;
import com.jason.crawler.Crawler;
import com.jason.crawler.annotation.PageFieldSelect;
import com.jason.crawler.annotation.PageSelect;
import com.jason.crawler.conf.CrawlerConf;
import com.jason.crawler.parser.PageParser;
import com.jason.crawler.util.FileUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 爬虫示例03：爬取页面，下载图片文件
 *
 * @author jason 2017-10-09 19:48:48
 */
public class CrawlerTest03 {

    @PageSelect(cssQuery = "body")
    public static class PageVo {

        @PageFieldSelect(cssQuery = "img", selectType = CrawlerConf.SelectType.ATTR, selectVal = "abs:src")
        private List<String> images;

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "images=" + images +
                    '}';
        }
    }

    public static void main(String[] args) {

        Crawler crawler = new Crawler.Builder()
                .setUrls("https://gitee.com/xuxueli0323/projects?page=1")
                .setWhiteUrlRegexs("https://gitee\\.com/xuxueli0323")
                .setThreadCount(3)
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {

                        // 文件信息
                        String filePath = "/Users/kmg/Downloads/tmp";

                        if (pageVo.getImages()!=null && pageVo.getImages().size() > 0) {
                            Set<String> imagesSet = new HashSet<>(pageVo.getImages());
                            for (String img: imagesSet) {

                                // 下载图片文件
                                String fileName = FileUtil.getFileNameByUrl(img, null);
                                long fileSize= HttpUtil.downloadFile(img,
                                        cn.hutool.core.io.FileUtil.file(filePath));
                                System.out.println("下载:"+fileName+"完成,大小为:"+fileSize);
//                                boolean ret = FileUtil.downFile(img, CrawlerConf.TIMEOUT_MILLIS_DEFAULT, filePath, fileName);
//                                System.out.println("down images " + (ret?"success":"fail") + "：" + img);
                            }
                        }
                    }
                })
                .build();

        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
