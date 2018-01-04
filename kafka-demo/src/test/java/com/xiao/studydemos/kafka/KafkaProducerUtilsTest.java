package com.xiao.studydemos.kafka;

import com.alibaba.fastjson.JSONObject;
import com.xiao.demo.commons.pojos.entity.TestEntity;
import com.xiao.studydemos.BaseTest;
import com.xiao.studydemos.kafka.utils.KafkaProducerUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Before;
import org.junit.Test;

import java.util.Properties;

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


    @Test
    public void testProduceMsg() throws Exception {
        Producer<String,String> producer = KafkaProducerUtils.producer();

        int sum = 10000;
        long sleep = 1000;

        while (sum > 0){
            String s = RandomStringUtils.randomAlphanumeric(4);
            String id = String.valueOf(RandomUtils.nextInt(1,4));
            String key = RandomStringUtils.randomAlphanumeric(4);
            String url = RandomStringUtils.randomAlphanumeric(4);
            String timestamp = String.valueOf(System.currentTimeMillis());

            TestEntity en = new TestEntity(id,key,url,timestamp);

            producer.send(new ProducerRecord<>("test_topic",s, JSONObject.toJSONString(en)));

            producer.flush();
            Thread.sleep(sleep);
            sum --;
        }

    }

}