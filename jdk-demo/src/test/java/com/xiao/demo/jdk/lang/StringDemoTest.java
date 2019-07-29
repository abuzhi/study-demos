package com.xiao.demo.jdk.lang;

import org.junit.Test;

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
}