package com.xiao.studydemos;

import com.xiao.studydemos.sorts.Constants;

import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        int[] arr = Constants.SORT_ARR;
        System.out.println( "Hello World!" );
        Arrays.sort(arr);
        System.out.println(Arrays.toString(arr));

    }
}
