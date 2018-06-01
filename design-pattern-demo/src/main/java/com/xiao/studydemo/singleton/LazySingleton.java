package com.xiao.studydemo.singleton;

/**
 * 懒汉模式，双重检查锁
 */
public class LazySingleton {

    private static volatile LazySingleton instance = null;

    private LazySingleton(){

    }

    public static LazySingleton getInstance(){
        if(instance==null){
            synchronized (LazySingleton.class){
                if(instance==null){
                    instance = new LazySingleton();
                }
            }
        }
        return instance;
    }

}
