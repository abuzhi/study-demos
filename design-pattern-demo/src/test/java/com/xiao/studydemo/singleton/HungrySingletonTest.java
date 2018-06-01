package com.xiao.studydemo.singleton;

import org.junit.Test;

import static org.junit.Assert.*;

public class HungrySingletonTest {

    @Test
    public void testSingle() {
        HungrySingleton singleton = HungrySingleton.getInstance();

    }
}