package com.jason.crawler.test;

import com.jason.crawler.Crawler;
import com.jason.crawler.annotation.PageFieldSelect;
import com.jason.crawler.annotation.PageSelect;
import com.jason.crawler.conf.CrawlerConf;
import com.jason.crawler.parser.PageParser;
import com.jason.crawler.loader.strategy.HtmlUnitPageLoader;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 爬虫示例07：JS渲染方式采集数据，"htmlUnit" 方案
 * (仅供学习测试使用，如有侵犯请联系删除； )
 *
 * @author jason 2017-12-29 23:29:48
 */
public class CrawlerTest07 {
    private static Logger logger = LoggerFactory.getLogger(CrawlerTest05.class);

    @PageSelect(cssQuery = "body")
    public static class PageVo {

        @PageFieldSelect(cssQuery = "#jd-price", selectType = CrawlerConf.SelectType.TEXT)
        private String data;

        public String getData() {
            return data;
        }

        public void setData(String data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {

        // 构造爬虫
        Crawler crawler = new Crawler.Builder()
                .setUrls("https://item.jd.com/12228194.html")
                .setAllowSpread(false)
                .setPageLoader(new HtmlUnitPageLoader())        // HtmlUnit 版本 PageLoader：支持 JS 渲染
                .setPageParser(new PageParser<PageVo>() {
                    @Override
                    public void parse(Document html, Element pageVoElement, PageVo pageVo) {
                        if (pageVo.getData() != null) {
                            logger.info("商品价格（JS动态渲染方式获取）: {}", pageVo.getData());
                        } else {
                            logger.info("商品价格（JS动态渲染方式获取）: 获取失败");
                        }

                    }
                })
                .build();

        // 启动
        crawler.start(true);

    }

}
