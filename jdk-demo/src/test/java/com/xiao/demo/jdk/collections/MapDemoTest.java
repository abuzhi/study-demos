package com.xiao.demo.jdk.collections;

import org.junit.Test;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/9/26.
 */
public class MapDemoTest {

    @Test
    public void testMap() throws Exception {

        Map<String,Object> map = new HashMap<>();

        Hashtable  hashtable = new Hashtable();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();


    }


    @Test
    public void testHashMap() throws Exception {
        Map<String,Object> map = new HashMap<>();
    }
}