package com.xiao.demo.jdk.lang;

/**
 * Created by xiao on 2019/11/27.
 * 测试ProcessBuilder 类，及Runtime.getRuntime().exec方式的不同
 * java创建线程方式的区别
 */
public class ProcessDemo {

    public static void main(String[] args) throws Exception {
        System.out.println("This is a program test about Process, ProcessBuilder, Runtime.exec etc.");
        System.out.println("Now Print the args:");
        for(int i=0;i<args.length;i++){
            System.out.println("	[args-"+i+"]:"+args[i]);
        }
        String sleepTime = args[0];
        int k = Integer.valueOf(sleepTime);
        while (k>0){
            System.out.println(k);
            k--;
            Thread.sleep(1000);
        }
    }
}
