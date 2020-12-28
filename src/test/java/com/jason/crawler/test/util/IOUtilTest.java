package com.jason.crawler.test.util;

import com.jason.crawler.util.IOUtil;

/**
 * io util test
 *
 * @author jason 2017-11-08 13:33:04
 */
public class IOUtilTest {

    public static void main(String[] args) {
        System.out.println(IOUtil.toString(IOUtil.toInputStream("qwe123阿斯达", null), null));
    }

}
