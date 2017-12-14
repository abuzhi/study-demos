package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang
 * 2017.12.14 17:18
 *
 * @Version 1.0
 */
public class KafkaConsumerUtilsTest extends BaseTest{

    private Properties properties ;

    @Before
    public void init(){
        String path = new File("").getAbsolutePath() + "/src/test/resources/config.properties";
        properties = super.getProperties("config.properties");
        KafkaConsumerUtils.init(path);
    }

    @Test
    public void consumer() throws Exception {
        String topic = properties.getProperty("consumer.topic");
        Consumer<String,String> consumer = KafkaConsumerUtils.consumer();
        consumer.subscribe(Arrays.asList(topic.split(",")));

        int count = 100;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records){
                count -- ;
                System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
                if(count<0){
                    break;
                }
            }
        }
    }

}