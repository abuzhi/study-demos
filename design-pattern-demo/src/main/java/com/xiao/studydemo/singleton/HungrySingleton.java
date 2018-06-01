package com.xiao.studydemo.singleton;

/**
 * 饿汉模式单例
 *
 */
public class HungrySingleton {

    private static HungrySingleton instance = new HungrySingleton();

    private HungrySingleton() {
    }

    public static HungrySingleton getInstance(){
        return instance;
    }

}
