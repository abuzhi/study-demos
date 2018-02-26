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

    @Test
    public void testHash() throws Exception {
        Object k = "test";
        int hash = 0;
        boolean useAltHashing = true;
        int hashSeed = sun.misc.Hashing.randomHashSeed(this);

        int h = 0;
        if (useAltHashing) {
            if ( k instanceof String) {
                hash = sun.misc.Hashing.stringHash32((String) k);
                System.out.println(hash);
            }
            h = hashSeed;
        }

        h ^= k.hashCode();

        // This function ensures that hashCodes that differ only by
        // constant multiples at each bit position have a bounded
        // number of collisions (approximately 8 at default load factor).
        h ^= (h >>> 20) ^ (h >>> 12);
        hash = h ^ (h >>> 7) ^ (h >>> 4);

        System.out.println(hash);

    }
}