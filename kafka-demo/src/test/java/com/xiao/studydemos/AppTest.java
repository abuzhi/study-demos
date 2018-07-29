package com.xiao.studydemos;

import org.junit.Test;

import java.util.Stack;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang
 * 2017.12.14 17:18
 *
 * @Version 1.0
 */
public class AppTest {

    @Test
    public void testName() throws Exception {
        String str = "(((()()())))";
        this.compare(str);

    }

    public boolean compare1(String str) {

        char[] arr = str.toCharArray();//转成数组
        int count = 0;

        if (arr.length < 2) {
            System.out.println("not ok  ");
            return false;
        }

        char tmp = arr[0];
        if (')' == tmp) {
            System.out.println("not ok  ");
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
            if ('(' == tmp) {
                count++;
            } else if (')' == tmp) {
                char outStr = arr[i - 1];
                if ('(' == outStr) {
                    count--;
                }
            }
        }

        if (count > 0) {
            System.out.println("not ok ");
            return false;
        }

        System.out.println("is ok ...");
        return true;
    }

    public boolean compare(String str) {

        char[] arr = str.toCharArray();//转成数组
        int count = 0;

        if (arr.length < 2) {
            System.out.println("not ok  ");
            return false;
        }

        char tmp = arr[0];
        if (')' == tmp) {
            System.out.println("not ok  ");
            return false;
        }

        for (int i = 0; i < arr.length; i++) {
            tmp = arr[i];
            if ('(' == tmp) {
                count++;
            } else if (')' == tmp) {
//判断count是否为0，为0可以直接返回false

//不为0直接count--，因为count是用来计算(的个数. 左括号永远要比右括号先出现才匹配
//所以count不为0时，直接--，为0则表示右括号先于左括号出现了，肯定不匹配，为false
                if (count == 0) {
                    return false;
                }
                count--;
            }
        }

        System.out.println("is ok ...");
        return true;
    }

}