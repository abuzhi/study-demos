package com.xiao.demo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoliang on 2017/3/22.
 * https://leetcode.com/problems/two-sum/#/description
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.

 You may assume that each input would have exactly one solution, and you may not use the same element twice.

 Example:
 Given nums = [2, 7, 11, 15], target = 9,

 Because nums[0] + nums[1] = 2 + 7 = 9,
 return [0, 1].

 */
public class T1_Two_Sum {

    //遍历
    public int[] twoSum(int[] nums, int target) {

        for(int i=0,len=nums.length; i<len; i++){
            int a = nums[i];
            for(int j=nums.length-1 ; j>i;j--){
                int b = nums[j];
                if(a + b == target){
                    int[] tmp = {i,j};
                    return tmp;
                }
            }
        }
        return null;
    }

    //用空间换时间
    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for(int i=0,len = nums.length; i<len; i ++){
            int a = nums[i];
            int b = target - a;

            if(map.containsKey(b)){
                int a1 = map.get(b);
                return new int[]{a1, i};
            }
            map.put(a,i);
        }

        return new int[]{0, 0};
    }

    public static void main(String[] args) {
        T1_Two_Sum t = new T1_Two_Sum();
        int target = 9;
        int[] arr = {2, 7, 11, 15};
        int[] nums = t.twoSum(arr,target);
        int[] nums2 = t.twoSum2(arr,target);
    }
}
