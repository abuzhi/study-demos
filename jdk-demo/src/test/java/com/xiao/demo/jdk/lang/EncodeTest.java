package com.xiao.demo.jdk.lang;

import org.junit.Test;

import java.util.Base64;

/**
 * Created by xiao on 2018/7/19.
 */
public class EncodeTest {
    @Test
    public void encode() throws Exception {

        String text = "{\"metric_en\":\"dau\",\"metric_ch\":\"外卖\"}";
        byte[] textByte = text.getBytes("UTF-8");

        Base64.Encoder encoder = Base64.getEncoder();
        String encodedText = encoder.encodeToString(textByte);
        System.out.println(encodedText);
    }
}
