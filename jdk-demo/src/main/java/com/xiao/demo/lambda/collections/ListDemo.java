package com.xiao.demo.lambda.collections;

import com.xiao.demo.pojo.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by xiao on 2018/10/18.
 * 测试 steam 类型操作
 */
public class ListDemo {
    private static List<Person> list = null;

    static {
        Person p1 = new Person(1L, "t1", 10);
        Person p2 = new Person(2L, "t2", 20);
        Person p3 = new Person(3L, "t3", 30);
        Person p4 = new Person(4L, "t4", 40);
        list = new ArrayList<>(4);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
    }

    /**
     * filter测试
     */
    public void filter() {
        List r1 = list.stream().filter(p -> {
            p.setName(p.getName() + " filter after.");
            return p.getAge() > 20;
        }).collect(Collectors.toList());
        System.out.println(Arrays.toString(r1.toArray()));

        List r2 = list.stream().filter(p -> (p.getAge() > 20 && p.getId() > 2)).collect(Collectors.toList());
        System.out.println(Arrays.toString(r2.toArray()));

        Optional<Person> op1 = list.stream().findAny();
        Optional<Person> op2 = list.stream().findFirst();
        System.out.println(op1.get().toString());
        System.out.println(op2.get().toString());
    }

    public void map() {
    }

    public static void main(String[] args) {
        ListDemo demo = new ListDemo();
        demo.filter();
    }

}
