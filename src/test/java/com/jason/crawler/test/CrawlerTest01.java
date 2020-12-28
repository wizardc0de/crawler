package com.jason.crawler.test;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.jason.crawler.Crawler;
import com.jason.crawler.annotation.PageFieldSelect;
import com.jason.crawler.annotation.PageSelect;
import com.jason.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.sql.SQLException;

/**
 * 爬虫示例01：爬取页面数据并封装VO对象
 *
 * @author jason 2017-10-09 19:48:48
 */
public class CrawlerTest01 {

    @PageSelect(cssQuery = ".view-container")
    public static class PageVo {

        @PageFieldSelect(cssQuery = ".article-title")
        private String repository;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @PageFieldSelect(cssQuery = ".markdown-body")
        private String content;

        @PageFieldSelect(cssQuery = ".description")
        private String description;

        @PageFieldSelect(cssQuery = ".watch")
        private String watch;

        @PageFieldSelect(cssQuery = ".star")
        private String star;

        @PageFieldSelect(cssQuery = ".fork")
        private String fork;

        public String getRepository() {
            return repository;
        }

        public void setRepository(String repository) {
            this.repository = repository;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }


        public String getWatch() {
            return watch;
        }

        public void setWatch(String watch) {
            this.watch = watch;
        }

        public String getStar() {
            return star;
        }

        public void setStar(String star) {
            this.star = star;
        }

        public String getFork() {
            return fork;
        }

        public void setFork(String fork) {
            this.fork = fork;
        }

        @Override
        public String toString() {
            return "PageVo{" +
                    "repository='" + repository + '\'' +
                    ", content='" + content + '\'' +
                    ", description='" + description + '\'' +
                    ", watch='" + watch + '\'' +
                    ", star='" + star + '\'' +
                    ", fork='" + fork + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        Crawler crawler = new Crawler.Builder()
                .setUrls("https://juejin.cn/post/6907899385284460557")
//                .setWhiteUrlRegexs("https://gitee\\.com/xuxueli0323/projects\\?page=\\d+")
                .setThreadCount(3)
                .setAllowSpread(false)
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {
                        // 解析封装 PageVo 对象
                        String pageUrl = html.location();
                        try {
                            Db.use().insert(Entity.create("post").set("title",pageVo.getRepository()).set("content",
                                    pageVo.getContent()));
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        }
                        System.out.println(pageUrl + "：" + pageVo.toString());
                    }
                })
                .build();
        System.out.println("start");
        crawler.start(true);
        System.out.println("end");
    }

}
