package com.xiao.studydemos.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.commons.utils.JdbcUtils;

import java.sql.Connection;
import java.util.Properties;

/**
 * Created by xiaoliang on 2018/1/4.
 */
public class KafkaToRdbConsumer implements Runnable{

    private Connection connection = null;
    private  JdbcUtils jdbc = null;

    public void init(Properties properties) throws Exception{
        JdbcUtils jdbc = new JdbcUtils(properties);
        connection = jdbc.getConnection();
        connection.setAutoCommit(false);
    }

    public void close() throws Exception{
        if(jdbc!=null){
            jdbc.closeConnection(connection);
        }
    }

    @Override
    public void run() {

    }

    public boolean consumer(String msg) throws Exception {
        JSONObject object = JSONObject.parseObject(msg);
        String tableName = "t_test_"+ object.getString("id");

        String sql = "insert " + tableName + " ";

        return true;
    }
}
