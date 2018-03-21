package com.xiao.demo.jdk.lang;

import org.junit.Test;

/**
 * Created by xiao on 2018/3/20.
 */
public class MathTest {

    @Test
    public void testMath() throws Exception {
        double a=35;
        double b=20;
        double c = 1.1111;
        System.out.println("c===>"+c);   //1.75
        System.out.println("c===> "+Math.ceil(c)); //2.0
        System.out.println("c===> "+(int) Math.ceil(c)); //2.0
        System.out.println(Math.floor(c));  //1.0
    }
}
