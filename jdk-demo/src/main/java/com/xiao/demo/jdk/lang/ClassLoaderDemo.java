package com.xiao.demo.jdk.lang;

import com.xiao.demo.App;

public class ClassLoaderDemo {

    public static void main(String[] args) {
        System.out.println(App.class.getClass().getResource("/"));
        System.out.println(App.class.getClass().getResource("/").getPath());

        System.out.println(App.class.getClass().getResource(""));

        ClassLoaderDemo demo = new ClassLoaderDemo();

        System.out.println(demo.getClass().getClassLoader().getResource(""));
        System.out.println(demo.getClass().getClassLoader().getResource("/"));

    }
}
