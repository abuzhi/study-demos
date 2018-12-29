package com.xiao.studydemo.proxy;

/**
 * Created by xiao on 2018/12/27.
 */
public class AnimalProxy implements Animal {

    private Animal animal;

    public AnimalProxy(Animal animal) {
        this.animal = animal;
    }

    /**
     * 代理方法
     */
    @Override
    public void canRun() {
        animal.canRun();
    }
}
