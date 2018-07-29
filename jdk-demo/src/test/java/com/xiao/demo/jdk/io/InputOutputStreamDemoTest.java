package com.xiao.demo.jdk.io;

import com.xiao.demo.App;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

import java.io.*;
import java.nio.charset.Charset;

import static org.junit.Assert.*;

public class InputOutputStreamDemoTest {

    @Test
    public void testFileInput() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(filepath);
            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            byte[] buf = new byte[3];
            int len  =0;
            //循环来读取该文件中的数据
            while((len=fileInputStream.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
            }
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
    public void testFileOutput() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-out.txt";
        System.out.println(filepath);
        assertNotNull(filepath);

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filepath);
            for(int i = 0;i<10;i++){
                String str = RandomStringUtils.randomAlphabetic(10) + "\n";
                fileOutputStream.write(str.getBytes(Charset.forName("utf-8")));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileInOutStream() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        assertNotNull(filepath);
        String fileoutpath = path + "test-out.txt";
        System.out.println(fileoutpath);

        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;


        try {
            fileInputStream = new FileInputStream(filepath);
            fileOutputStream = new FileOutputStream(fileoutpath);

            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            byte[] buf = new byte[3];
            int len  =0;
            //循环来读取该文件中的数据
            while((len=fileInputStream.read(buf))!=-1){
                String str = new String(buf,0,len,Charset.forName("utf-8"));
                System.out.print(str);
                fileOutputStream.write(buf);
            }
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
    public void testBufferInput() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);

        try {
            InputStream inputStream = new FileInputStream(filepath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            byte[] buf = new byte[3];
            int len  =0;
            //循环来读取该文件中的数据
            while((len=bufferedInputStream.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
            }

            inputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testBufferOut() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-out.txt";
        System.out.println(filepath);
        assertNotNull(filepath);

        try {
            OutputStream outputStream = new FileOutputStream(filepath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            for(int i = 0;i<10;i++){
                String str = RandomStringUtils.randomAlphabetic(10) + "\n";
                bufferedOutputStream.write(str.getBytes(Charset.forName("utf-8")),0,str.length());
            }

            bufferedOutputStream.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBufferInOutStream() {
        String path = App.class.getResource("/").getPath();
        //test class 时，加载路径为test-class下
        String filepath = path + "test-text.txt";
        System.out.println(filepath);
        String fileoutpath = path + "test-out.txt";

        try {
            InputStream inputStream = new FileInputStream(filepath);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);

            OutputStream outputStream = new FileOutputStream(fileoutpath);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);

            //定义一个字节缓冲区,该缓冲区的大小根据需要来定义
            byte[] buf = new byte[3];
            int len  =0;
            //循环来读取该文件中的数据
            while((len=bufferedInputStream.read(buf))!=-1){
                String str = new String(buf,0,len);
                System.out.print(str);
                //字节读取的方式，中文会有乱码，输出时指定编码
                bufferedOutputStream.write(str.getBytes(Charset.forName("utf-8")));
            }

            bufferedOutputStream.flush();
            inputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}