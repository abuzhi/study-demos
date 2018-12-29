package com.xiao.studydemo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by xiao on 2018/12/27.
 */
public class ObjectHandler implements InvocationHandler {

    private Object object;

    public ObjectHandler(Object object) {
        this.object = object;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(System.currentTimeMillis());
        Object result = method.invoke(object, args);
        System.out.println(System.currentTimeMillis());
        return result;
    }
}
