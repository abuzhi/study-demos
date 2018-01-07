package com.xiao.studydemos.kafka.consumer;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.commons.utils.JdbcUtils;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.util.List;
import java.util.Properties;
import java.util.Queue;

/**
 * Created by xiaoliang on 2018/1/4.
 */
public class KafkaToRdbConsumer implements Runnable{

    public static final Logger logger = LoggerFactory.getLogger(KafkaToRdbConsumer.class);

    private Connection connection = null;
    private  JdbcUtils jdbc = null;
    private Queue<List<ConsumerRecord<String, String>>> queue = null;

    public void init(Properties properties) throws Exception{
        JdbcUtils jdbc = new JdbcUtils(properties);
        connection = jdbc.getConnection();
        connection.setAutoCommit(false);
    }

    public Queue getQueue() {
        return queue;
    }

    public void setQueue(Queue queue) {
        this.queue = queue;
    }

    public void close() throws Exception{
        if(jdbc!=null){
            jdbc.closeConnection(connection);
        }
    }

    @Override
    public void run() {
        while(true){
            if(queue.isEmpty()){
                try {
                    Thread.sleep(100);
                    continue;
                } catch (InterruptedException e) {
                    logger.error("sleep error : ",e);
                }
            }

            try {
                consumer();
            } catch (Exception e) {
                logger.error("consumer error : ",e);
            }
        }
    }

    public boolean consumer() throws Exception {
        Object msg = queue.poll();
//        JSONObject object = JSONObject.parseObject(msg);
//        String tableName = "t_test_"+ object.getString("id");
//
//        String sql = "insert " + tableName + " ";

        return true;
    }
}
