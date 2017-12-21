package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import kafka.admin.AdminClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import scala.Option;
import scala.collection.JavaConversions;
import scala.collection.immutable.*;

import java.io.File;
import java.util.*;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by xiaoliang
 * 2017.12.15 14:13
 *
 * @Version 1.0
 */
public class KafkaAdminUtilsTest extends BaseTest {

    private Properties properties;

    private AdminClient admin;

    @Before
    public void init() {
        String path = new File("").getAbsolutePath() + "/src/test/resources/config.properties";
        properties = super.getProperties("config.properties");
        KafkaConsumerUtils.init(path);

        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getProperty("bootstrap.servers"));
        props.put("enable.auto.commit", "true");
        props.put("client.id", "test");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        admin = AdminClient.create(props);
    }


    @Test
    public void testAdmin() throws Exception {
        AdminClient.ConsumerGroupSummary cgs = admin.describeConsumerGroup("test-cube-work", 0);
        //scala list转java List
        Option<scala.collection.immutable.List<AdminClient.ConsumerSummary>> bbbb = cgs.consumers();
        scala.collection.immutable.List<AdminClient.ConsumerSummary> consumerSummaryList_s = bbbb.get();
        List<AdminClient.ConsumerSummary> consumerSummaryList = JavaConversions.seqAsJavaList(consumerSummaryList_s);

    }

    @Test
    public void testResetOffsetByTime() throws Exception {


    }

    @After
    public void close() {
        admin.close();
    }
}