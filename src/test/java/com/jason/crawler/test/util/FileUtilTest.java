package com.jason.crawler.test.util;

import com.jason.crawler.conf.CrawlerConf;
import com.jason.crawler.util.FileUtil;
import org.junit.Test;

/**
 * page downloader test
 *
 * @author jason 2017-10-09 17:47:13
 */
public class FileUtilTest {

    /**
     * 生成Html本地文件
     */
    @Test
    public void saveFileTest() {

        String htmlData = "<html>Hello world.</html>";
        String filePath = "/Users/kmg/Downloads/tmp";
        String fileName = FileUtil.getFileNameByUrl("http://www.baidu.com/",	CrawlerConf.CONTENT_TYPE_HTML);

        FileUtil.saveFile(htmlData, filePath, fileName);
    }

}

