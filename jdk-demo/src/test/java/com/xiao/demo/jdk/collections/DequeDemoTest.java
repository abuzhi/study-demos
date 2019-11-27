package com.xiao.demo.jdk.collections;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by xiao on 2019/11/20.
 */
public class DequeDemoTest {


    @Test
    public void testMin() {
        PriorityQueue<People> queue = new PriorityQueue<People>(11,
                new Comparator<People>() {
                    public int compare(People p1, People p2) {
                        return p2.age - p1.age;
                    }
                });

        for (int i = 1; i <= 10; i++) {
            queue.add(new People("张"+ i, (new Random().nextInt(100))));
        }

        People min = queue.peek();
        System.out.println(min);

        min.age = 11;

        System.out.println(queue.peek());

    }

    class People {
        String name;
        int age;
        public People(String name, int age){
            this.name = name;
            this.age = age;
        }
        public String toString() {
            return "姓名："+name + " 年龄：" + age;
        }
    }


}