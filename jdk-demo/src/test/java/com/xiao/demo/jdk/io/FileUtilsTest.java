package com.xiao.demo.jdk.io;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;

public class FileUtilsTest {


    @Test
    public void input() throws Exception {
        String path = "/opt/tmp/cubes.json";
        File file = new File(path);
        InputStream inputStream = new FileInputStream(file);
        List<String> list = IOUtils.readLines(inputStream, Charset.forName("utf-8"));
        String s = list.get(0);

        JSONArray array = JSONArray.parseArray(s);
        for(int i = 0;i<array.size();i++){
            JSONObject cube = array.getJSONObject(i);
            boolean ready = cube.getString("status").equalsIgnoreCase("READY") ? true : false;
            if(!ready){
                System.out.println(i + "-> " + cube.getString("name") +" -> false");
                continue;
            }
            JSONArray arr = cube.getJSONArray("segments");
            for(int j = 0;j<arr.size();j++){
                JSONObject seg = arr.getJSONObject(j);
                String dictionaries =  seg.getString("dictionaries");
                System.out.println(i + "-> " + j + " -> " + cube.getString("name") +" -> " + dictionaries);
            }

        }

    }
}
