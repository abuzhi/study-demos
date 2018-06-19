package com.xiao.demo.jdk.lang;

import com.xiao.demo.App;
import org.junit.Test;

import static org.junit.Assert.*;

public class ClassLoaderDemoTest {

    @Test
    public void testClassPath() {
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("/").getPath());

        System.out.println(this.getClass().getResource(""));
        System.out.println(this.getClass().getResource("").getPath());

        System.out.println(App.class.getClass().getResource("/"));
        System.out.println(App.class.getClass().getResource("/").getPath());

        System.out.println(App.class.getClass().getResource(""));
    }
}