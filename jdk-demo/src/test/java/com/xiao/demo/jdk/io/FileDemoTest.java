package com.xiao.demo.jdk.io;

import com.xiao.demo.App;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class FileDemoTest {

    /**
     * 目录操作
     */
    @Test
    public void testFolder() {
        String path = App.class.getResource("/").getPath();
        String folder = "test-folder";

        File file = new File(path + folder);

        if(!file.exists()){
            file.mkdir();
            String[] files = file.getParentFile().list();
            System.out.println(Arrays.toString(files));

            file.delete();//删除目录，只删除空目录
        }else {
            String[] files = file.getParentFile().list();
            System.out.println(Arrays.toString(files));
        }

    }

    /**
     * 文件操作
     */
    @Test
    public void testFile() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-file.txt";
        System.out.println(filepath);
        File file = new File(filepath);

        if(!file.exists()){
            //创建文件
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            String[] files = file.getParentFile().list();
            System.out.println(Arrays.toString(files));

            //删了文件
            file.delete();

        }else {
            String[] files = file.getParentFile().list();
            System.out.println(Arrays.toString(files));
        }
    }

    /**
     * 迭代删除文件，文件夹
     */
    @Test
    public void testFolderDelete() {

    }
}