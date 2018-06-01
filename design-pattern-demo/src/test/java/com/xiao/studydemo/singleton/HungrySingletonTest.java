package com.xiao.studydemo.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class HungrySingletonTest {

    @Test
    public void testSingle() {
        HungrySingleton singleton = HungrySingleton.getInstance();

    }

    @Test
    public void testEnum() {
        EnumSingleton singleton = EnumSingleton.SINGLETON;

    }

    @Test
    public void testLazy() {
        LazySingleton singleton = LazySingleton.getInstance();

    }
}