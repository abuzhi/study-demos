package com.xiao.demo.jdk.lang;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StringDemoTest {


    @Test
    public void test() {
        String a = new String("12");
        String b = new String("3");
        String c = "123";
        String d = a + b;
        System.out.println(c == d);
        System.out.println(c.equals(d));
        System.out.println(c == d.intern());
    }

    @Test
    public void testBuilder() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("1")
                .append("2")
                .append("3");

        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("a")
                .append("b")
                .append("c");
    }

    @Test
    public void testEqualsAndContains() {

        int len = 10000000;
        String homePageId = "#12040001#40000120#";
        List<String> data = new ArrayList<>(len);
        long start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            data.add(RandomStringUtils.randomAlphabetic(4,10));
        }
        long end = System.currentTimeMillis();
        System.out.println("init ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            homePageId.equals(data.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("equals ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            homePageId.equals("#"+data.get(i)+"#");
        }
        end = System.currentTimeMillis();
        System.out.println("equals and ms= " + (end-start));


        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            homePageId.contains(data.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("contains ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            homePageId.contains("#"+data.get(i)+"#");
        }
        end = System.currentTimeMillis();
        System.out.println("contains and ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            homePageId.contains("#".concat(data.get(i)).concat("#"));
        }
        end = System.currentTimeMillis();
        System.out.println("contains concat ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            StringBuilder sb = new StringBuilder("#");
            sb.append(data.get(i)).append("#");
            homePageId.contains(sb.toString());
        }
        end = System.currentTimeMillis();
        System.out.println("contains sb ms= " + (end-start));

        start = System.currentTimeMillis();
        String[] pages = homePageId.split("#");
        for(int i=0;i<len;i++){
            containsPage(pages,data.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("contains page custom ms= " + (end-start));

        start = System.currentTimeMillis();
        for(int i=0;i<len;i++){
            "1231111".equals(data.get(i));
            "2345333333".equals(data.get(i));
        }
        end = System.currentTimeMillis();
        System.out.println("equals if ms= " + (end-start));

    }

    private boolean containsPage(String[] pages,String pageId){
        for(String p : pages){
            if(p.equals(pageId)){
                return true;
            }
        }
        return false;
    }

    private boolean equalsPage(String[] pages,String pageId){
        for(String p : pages){
            if(p.equals(pageId)){
                return true;
            }
        }
        return false;
    }
}