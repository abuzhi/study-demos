package com.xiao.demo;

import java.text.DecimalFormat;

/**
 * Created by xiaoliang on 2017/3/26.
 * <p>
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * <p>
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * <p>
 * Example 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * <p>
 * The median is 2.0
 * Example 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * <p>
 * The median is (2 + 3)/2 = 2.5
 *
 * 重点是归并排序，适合排序已经排好的两个数组之间
 */
public class T4_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int len1 = nums1.length, len2 = nums2.length, i = 0, j = 0;
        int k = 0;
        int len = nums1.length + nums2.length;
        int[] sort = new int[len];


        if(len1 ==0){
            while (j < len2) {
                sort[k] = nums2[j];
                k++;
                j++;
            }
        }else if(len2==0){
            while (i < len1) {
                sort[k] = nums1[i];
                k++;
                i++;
            }
        }else {
            while (i < len1 || j < len2) {
                if (nums1[i] < nums2[j]) {
                    sort[k] = nums1[i];
                    i++;
                    k++;
                } else {
                    sort[k] = nums2[j];
                    j++;
                    k++;
                }

                if (i == len1) {
                    while (j < len2) {
                        sort[k] = nums2[j];
                        k++;
                        j++;
                    }
                }
                if (j == len2) {
                    while (i < len1) {
                        sort[k] = nums1[i];
                        k++;
                        i++;
                    }
                }

            }
        }


        double mid = 0;
        if (len % 2 == 0) {
            mid = (sort[len / 2] + sort[len / 2 - 1]) / 2.0;
        } else {
            mid = sort[len / 2 ];
        }

        return mid;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {};

        T4_Median_of_Two_Sorted_Arrays sorted_arrays = new T4_Median_of_Two_Sorted_Arrays();

        double mid = sorted_arrays.findMedianSortedArrays(nums1, nums2);
        System.out.print("ok");
    }
}
