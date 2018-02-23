package com.xiao.demo.jdk.collections;

import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/5/21.
 */
public class ListDemoTest {

    @Test
    public void testArrayList(){
        List<String> list = new ArrayList<>();

        list.add(0,"0");

        list.add("test");

        List<Integer> list1 = new ArrayList<>();

    }

    @Test
    public void testLinkedList(){
        List<String> list = new LinkedList<>();

    }

    @Test
    public void testVector() throws Exception {
        List<String> v = new Vector<>(100);


    }
}