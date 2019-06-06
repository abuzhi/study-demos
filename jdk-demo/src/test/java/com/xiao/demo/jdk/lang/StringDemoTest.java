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
}