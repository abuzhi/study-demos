package com.xiao.studydemo.proxy;

/**
 * Created by xiao on 2018/12/27.
 */
public class AbstractDog implements Animal {

    @Override
    public void canRun() {
        System.out.println(" dog run ...");
    }
}
