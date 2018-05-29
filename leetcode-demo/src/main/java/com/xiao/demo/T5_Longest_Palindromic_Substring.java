package com.xiao.demo;

/**
 * Created by xiaoliang on 2017/3/26.
 * <p>
 * Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
 * <p>
 * Example:
 * <p>
 * Input: "babad"
 * <p>
 * Output: "bab"
 * <p>
 * Note: "aba" is also a valid answer.
 * Example:
 * <p>
 * Input: "cbbd"
 * <p>
 * Output: "bb"
 */
public class T5_Longest_Palindromic_Substring {

    public String longestPalindrome(String s) {
        char[] arrs = s.toCharArray();
        int maxLen = 0, len = arrs.length;

        String result = "";
        if (arrs.length < 2) {
            return s;
        }

        boolean is = len % 2 == 0 ? true : false;

        for (int i = 0; i < (len/2 + 1); i++) {

        }

        return "";
    }

    /**
     * 暴力算法(朴素匹配)
     * @param source
     * @return
     */
    public String bruteForce(String source){
        char[] arrs = source.toCharArray();
        int maxLen = 0, len = arrs.length;

        String result = "";
        if (arrs.length < 2) {
            return source;
        }
        boolean is = len % 2 == 0 ? true : false;

        for (int i = 0; i < (len/2 + 1); i++) {

        }

        return "";
    }


}
