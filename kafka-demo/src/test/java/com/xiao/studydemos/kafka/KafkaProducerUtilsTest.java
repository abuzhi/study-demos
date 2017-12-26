package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.Properties;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/12/16.
 */
public class KafkaProducerUtilsTest extends BaseTest {

    private Properties properties ;

    @Before
    public void init(){
//        String path = new File("").getAbsolutePath() + "/src/test/resources/config.properties";
        this.properties = super.getProperties("config.properties");
        KafkaProducerUtils.init(properties);
    }

    @Test
    public void producer() throws Exception {
        Producer<String,String> producer = KafkaProducerUtils.producer();

        for(int i =0;i<10;i++){
            String s = "test"+ RandomUtils.nextInt(100,9999);
            producer.send(new ProducerRecord<String, String>("test_topic",s,s));
        }

        producer.flush();
    }

}