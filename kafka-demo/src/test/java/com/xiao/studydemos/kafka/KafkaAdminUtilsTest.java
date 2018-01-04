package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import com.xiao.studydemos.kafka.utils.KafkaConsumerUtils;
import kafka.admin.AdminClient;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.common.TopicPartition;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Option;
import scala.collection.JavaConverters;

import java.io.File;
import java.util.*;
import java.util.List;

/**
 * Created by xiaoliang
 * 2017.12.15 14:13
 *
 * @Version 1.0
 */
public class KafkaAdminUtilsTest extends BaseTest {

    public static final Logger logger = LoggerFactory.getLogger(KafkaAdminUtilsTest.class);

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
        AdminClient.ConsumerGroupSummary cgs = admin.describeConsumerGroup("test-processor-work", 0);
        //scala list转java List
        Option<scala.collection.immutable.List<AdminClient.ConsumerSummary>> bbbb = cgs.consumers();
        scala.collection.immutable.List<AdminClient.ConsumerSummary> consumerSummaryList_s = bbbb.get();
        List<AdminClient.ConsumerSummary> consumerSummaryList = JavaConverters.seqAsJavaList(consumerSummaryList_s);
        List<Map<String, Object>> topicAndOffsets = new ArrayList<>();

        Consumer consumer = KafkaConsumerUtils.consumer();

        for (AdminClient.ConsumerSummary cs : consumerSummaryList) {
            scala.collection.immutable.List<TopicPartition> topicPartitionList_s = cs.assignment();
            List<TopicPartition> topicPartitionList = JavaConverters.seqAsJavaList(topicPartitionList_s);

            consumer.assign(topicPartitionList);
            Map<TopicPartition, Long> logsizes = consumer.endOffsets(topicPartitionList);

            for (TopicPartition tp : topicPartitionList) {
                Map<String, Object> map = new HashMap<>();
                Long offset = consumer.position(tp);
                Long logSize = logsizes.get(tp);
                map.put("topic", tp.topic());
                map.put("partition", tp.partition());
                map.put("offset", offset);
                map.put("logSize", logSize);
                map.put("lag", logSize - offset);

                topicAndOffsets.add(map);
                System.out.println("+++++++++++" + tp.topic() + "---" + tp.partition() + "----" + consumer.position(tp));
            }
            System.out.println("+++++++++++" + logsizes);

        }
        consumer.close();
    }

    @Test
    public void testResetOffsetByTime() throws Exception {


    }

    @After
    public void close() {
        admin.close();
    }
}