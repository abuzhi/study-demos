package com.xiao.studydemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiao on 2018/12/27.
 */
public class AnimalHandler implements InvocationHandler {

    private Animal animal;

    public AnimalHandler(Animal animal) {
        this.animal = animal;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(System.currentTimeMillis());
        Object result = method.invoke(animal, args);
        System.out.println(System.currentTimeMillis());
        return result;
    }
}
