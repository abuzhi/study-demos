package com.xiao.demo.jdk.lang;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2018/11/27.
 */
public class HashCodeDemoTest {


    @Test
    public void hashTest() throws Exception {
        String value = "Returns the index within this string of the first occurrence ofReturns the index within this string of the first occurrence ofReturns the index within this string of the first occurrence of" +
                "Returns the index within this string of the first occurrence ofReturns the index within this string of the first occurrence ofReturns the index within this string of the first occurrence of" +
                "Returns the index within this string of the first occurrence of";

        System.out.print(value.hashCode());

    }
}