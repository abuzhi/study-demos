package com.xiao.studydemo.factory;

import com.xiao.studydemo.pojo.Animal;
import com.xiao.studydemo.pojo.Cat;

public class WideAnimalFactory implements Factory {

    @Override
    public Animal createAnimal(String name) {
        if("tiger".equals(name)){
            return new Cat();
        }else {
            return null;
        }
    }
}
