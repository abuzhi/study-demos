package com.xiao.studydemos;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by xiaoliang
 * 2016.12.01 17:10
 *
 * @Version 1.0
 */
public class BaseTest {

    protected Properties getProperties(String name){
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            String configFilePath = classLoader.getResource(StringUtils.isBlank(name) ? "config.properties" : name).getFile();

            // 配置文件
            Properties properties = new Properties();
            FileInputStream fis = null;
            try{
                System.out.println(new File("").getAbsolutePath());
                File pfile = new File(configFilePath);
                fis = new FileInputStream(pfile);
            }catch(Exception e){
                e.printStackTrace();
            }
            properties.load(fis);
            return properties;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
