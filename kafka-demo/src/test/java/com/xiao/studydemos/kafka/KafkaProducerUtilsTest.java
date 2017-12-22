package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang on 2017/12/16.
 */
public class KafkaProducerUtilsTest extends BaseTest {

    @Test
    public void producer() throws Exception {

        Producer<String,String> producer = KafkaProducerUtils.producer();
    }

}