package com.xiao.studydemo.singleton;

public class InnerClassSingleton {

    private InnerClassSingleton(){

    }

    private static class Holder {
        private static InnerClassSingleton instance = new InnerClassSingleton();
    }

    private static InnerClassSingleton getInstance(){

        return Holder.instance;
    }
}
