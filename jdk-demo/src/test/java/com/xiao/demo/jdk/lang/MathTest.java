package com.xiao.demo.jdk.lang;

import org.junit.Test;

import java.text.DecimalFormat;

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

    @Test
    public void math() {
        DecimalFormat df = new DecimalFormat("#0");
        double d1 = 12.1123234213;
        double d2 = 12.5123212312;
        double d3 = 11.11;

        int d11 = Integer.valueOf(df.format(d1));
        int d22 = Integer.valueOf(df.format(d2));
        int d33 = Integer.valueOf(df.format(d3));

        System.out.println(d11);  //1.0
        System.out.println(d22);  //1.0
        System.out.println(d33);  //1.0

    }
}
