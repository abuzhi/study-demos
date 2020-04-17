package com.xiao.demo.jdk.lang;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2019/12/25.
 */
public class PatternDemoTest {
    @Test
    public void testReg() throws Exception {
        File file = new File("D:\\tmp\\user.txt");
        List<String> list = FileUtils.readLines(file,"GBK");
        String reg = "loginName\":\"(\\w*)\"";
        Pattern pattern = Pattern.compile(reg);
        for(String s : list){
            Matcher matcher = pattern.matcher(s);
            while (matcher.find()) {
                String tmp = matcher.group();
                System.out.println(tmp);
            }
        }

    }
}