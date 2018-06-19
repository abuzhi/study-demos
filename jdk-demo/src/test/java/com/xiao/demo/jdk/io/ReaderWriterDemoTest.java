package com.xiao.demo.jdk.io;

import com.xiao.demo.App;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class ReaderWriterDemoTest {

    @Test
    public void testReader() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(filepath);
            Reader reader = new InputStreamReader(inputStream,Charset.forName("utf-8"));
            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            char[] buf = new char[3];
            int len  =0;
            //循环来读取该文件中的数据
            while((len=reader.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testWriter() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-out.txt";
        System.out.println(filepath);
        assertNotNull(filepath);

        try {
            OutputStream fileOutputStream = new FileOutputStream(filepath);
            Writer writer = new  OutputStreamWriter(fileOutputStream);
            for(int i = 0;i<10;i++){
                String str = RandomStringUtils.randomAlphabetic(10) + "\n";
                writer.write(str);
            }

            writer.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testReaderWriter() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        String fileoutpath = path + "test-out.txt";
        System.out.println(fileoutpath);

        InputStream fileInputStream = null;
        OutputStream fileOutputStream = null;


        try {
            fileInputStream = new FileInputStream(filepath);
            fileOutputStream = new FileOutputStream(fileoutpath);

            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            char[] buf = new char[3];
            int len  =0;
            //循环来读取该文件中的数据
            Reader reader = new InputStreamReader(fileInputStream);
            Writer writer = new OutputStreamWriter(fileOutputStream);

            while((len=reader.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
                writer.write(buf);
            }

            writer.flush();

            writer.close();
            reader.close();

            fileInputStream.close();
            fileOutputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    @Test
    public void testFileReaderWriter() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        String fileoutpath = path + "test-out.txt";
        System.out.println(fileoutpath);

        try {
            FileReader fileReader = new FileReader(filepath);
            FileWriter fileWriter = new FileWriter(fileoutpath);
            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            char[] buf = new char[3];
            int len  =0;

            while((len=fileReader.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
                fileWriter.write(buf);
            }

            fileWriter.flush();

            fileReader.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBuffered() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        String fileoutpath = path + "test-out.txt";
        System.out.println(fileoutpath);

        try {
            Reader fileReader = new FileReader(filepath);
            Writer fileWriter = new FileWriter(fileoutpath);

            BufferedReader bufferedReader = new BufferedReader(fileReader);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = bufferedReader.readLine();
            while (line !=null ){
                System.out.println(line);
                bufferedWriter.write(line);
                bufferedWriter.newLine();
                line = bufferedReader.readLine();
            }

            bufferedWriter.flush();

            fileReader.close();
            fileWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}