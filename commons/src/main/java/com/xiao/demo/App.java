//package com.xiao.demo;
//
//import org.apache.commons.io.IOUtils;
//
//import java.io.InputStream;
//import java.util.Arrays;
//import java.util.List;
//
///**
// * Hello world!
// *
// */
//public class App {
//    public static void main( String[] args ) throws Exception{
//        String shell = args[0];
//        String encode = "UTF-8";
//        if(args.length>1){
//            encode = args[1];
//        }
//        Process process = Runtime.getRuntime().exec(shell);
//        InputStream in = process.getInputStream();
//        List<String> list = IOUtils.readLines(in,encode);
//
//        Arrays.toString(list.toArray());
//        System.out.println( Arrays.toString(list.toArray()));
//    }
//}
