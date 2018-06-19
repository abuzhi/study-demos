package com.xiao.studydemo.factory;

import com.xiao.studydemo.pojo.Animal;

public interface Factory {
    Animal createAnimal(String name);
}
