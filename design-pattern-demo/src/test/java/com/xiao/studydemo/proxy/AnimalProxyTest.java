package com.xiao.studydemo.proxy;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2018/12/27.
 */
public class AnimalProxyTest {


    /**
     * 静态代理一下
     * @throws Exception
     */
    @Test
    public void canRun() throws Exception {
        Animal monkey = new Monkey();
        AnimalProxy proxy = new AnimalProxy(monkey);
        proxy.canRun();
    }

}