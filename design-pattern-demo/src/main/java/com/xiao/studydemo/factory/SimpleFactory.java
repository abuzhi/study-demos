package com.xiao.studydemo.factory;

import com.xiao.studydemo.pojo.Animal;
import com.xiao.studydemo.pojo.Cat;
import com.xiao.studydemo.pojo.Duck;

/**
 * 简单工厂模式
 *
 */
public class SimpleFactory {

    public static Animal createAnimal (String name){
        if("cat".equals(name)){
            return new Cat();
        }else if("duck".equals(name)){
            return new Duck();
        }else {
            return null;
        }
    }
}
