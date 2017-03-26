package com.xiao.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoliang on 2017/3/23.
 * <p>
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */
public class T3_Longest_Substring_Without_Repeating_Characters {

    public int lengthOfLongestSubstring(String s) {
        int maxLen = 0;
        if (s.length() < 2) {
            return s.length();
        }

        char[] arrs = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0, j = 0,len = arrs.length; i < len; i++) {

            if (map.containsKey(arrs[i])) {
                int index = map.get(arrs[i]);
                //判断以前是否有出现过，取最大
                j = index >=j ? index+1 : j ;
            }
            map.put(arrs[i], i);
            maxLen = maxLen < (i + 1 - j) ? (i + 1 - j) : maxLen;
        }

        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abcabcbb";

        T3_Longest_Substring_Without_Repeating_Characters t3 = new T3_Longest_Substring_Without_Repeating_Characters();
        int max = t3.lengthOfLongestSubstring(s);
        System.out.println(max);
    }
}
