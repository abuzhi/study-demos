package com.xiao.studydemo.factory;

import com.xiao.studydemo.pojo.Animal;
import org.junit.Test;

import static org.junit.Assert.*;

public class FactoryTest {

    @Test
    public void testFactory() {

        Factory factory = new HomeAnimalFactory();
        Animal cat = factory.createAnimal("cat");

        Animal duck = factory.createAnimal("duck");

        factory = new WideAnimalFactory();
        Animal tiger = factory.createAnimal("tiger");

    }

    @Test
    public void simpleFactory() {
        Animal cat = SimpleFactory.createAnimal("cat");
        Animal duck = SimpleFactory.createAnimal("duck");

    }
}