package com.xiao.demo.jdk.io;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class FileDemoTest {

    @Test
    public void readFile() {
        String path = "/Users/xiao/workspaces/logs/test2.txt";
        File file = new File(path);
        InputStream in = null;
        try {
            in = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        List<String> list = null;
        try {
            list = IOUtils.readLines(in, Charset.forName("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        int line = 0;
        DateTime before = null;
        String pattern1 = "yyyy/MM/dd HH:mm:ss.SSS";
        String pattern_1 = "\\d\\d\\d\\d/\\d\\d/\\d\\d \\d\\d:\\d\\d:\\d\\d\\.\\d\\d\\d";

        String pattern2 = "yyyy-MM-dd HH:mm:ss,SSS";
        String pattern_2 = "\\d\\d\\d\\d-\\d\\d-\\d\\d \\d\\d:\\d\\d:\\d\\d\\,\\d\\d\\d";

        DateTimeFormatter formatter1 = DateTimeFormat.forPattern(pattern1);
        DateTimeFormatter formatter2 = DateTimeFormat.forPattern(pattern2);
        DateTime now = null;

        for(String s : list){
            line ++;
            if(StringUtils.isBlank(s) || s.length()<23){
                continue;
            }
            String time = s.substring(0,23);
            Pattern pattern = Pattern.compile(pattern_1);
            Matcher matcher = pattern.matcher(time);
            boolean isok = matcher.matches();
            if(isok){
                now = formatter1.parseDateTime(time);
                if(before==null){
                    before = now;
                }
            }else {
                pattern = Pattern.compile(pattern_2);
                matcher = pattern.matcher(time);
                isok = matcher.matches();
                if(isok){
                    now = formatter2.parseDateTime(time);
                    if(before==null){
                        before = now;
                    }
                }else {
                    continue;
                }
            }

            long end = now.getMillis();
            long start = before.getMillis();
            System.out.println(line + " -> " + (end - start));
            before = now;
        }
    }

    @Test
    public void testTime() {
        DateTime time = new DateTime();
    }

    @Test
    public void testSplit() {
        String s = "2018/06/14 14:22:05.098 main [DEBUG] DefaultListableBeanFactory (AbstractBeanFactory.java:251) Returning cached instance of singleton bean 'org.springframework.transaction.config.internalTransactionAdvisor'";
        int len = "2018/06/14 14:22:05.098".length();
        String time = s.substring(0,len);
        System.out.println(len + "=" + time);
    }
}