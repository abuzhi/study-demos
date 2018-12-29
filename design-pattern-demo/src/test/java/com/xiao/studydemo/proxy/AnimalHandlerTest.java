package com.xiao.studydemo.proxy;

import org.junit.Test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2018/12/27.
 */
public class AnimalHandlerTest {

    @Test
    public void test() throws Exception {
        //要代理的真实对象
        Animal monkey = new Monkey();
        //创建中介类实例
        InvocationHandler handler = new AnimalHandler(monkey);
        //获取类加载器
        ClassLoader clazz = monkey.getClass().getClassLoader();
        //动态产生一个代理类
        Animal proxy = (Animal) Proxy.newProxyInstance(clazz, monkey.getClass().getInterfaces(), handler);
        //通过代理类，执行doSomething方法；
        proxy.canRun();

    }
}