package com.xiao.demo.jdk.collections;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/5/21.
 */
public class SetDemoTest {
    @Test
    public void testSet() throws Exception {
        Set hashSet = new HashSet();
        Set treeSet = new TreeSet();
    }

    @Test
    public void testSetArray() throws Exception {

        Set<String> set = new HashSet();
        set.add("123");
        set.add("xxx");

        System.out.println(Arrays.toString(set.toArray()));
    }
}