package com.jason.crawler.loader.strategy;

import com.jason.crawler.loader.PageLoader;
import com.jason.crawler.model.PageRequest;
import com.jason.crawler.util.JsoupUtil;
import org.jsoup.nodes.Document;

/**
 * jsoup page loader
 *
 * @author jason 2017-12-28 00:29:49
 */
public class JsoupPageLoader extends PageLoader {

    @Override
    public Document load(PageRequest pageRequest) {
        return JsoupUtil.load(pageRequest);
    }

}
