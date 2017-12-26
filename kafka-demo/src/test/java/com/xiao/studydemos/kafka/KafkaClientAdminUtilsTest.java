package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.requests.DescribeLogDirsResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertNotNull;

/**
 * Created by xiaoliang
 * 2017.12.15 14:13
 *
 * @Version 1.0
 */
public class KafkaClientAdminUtilsTest extends BaseTest{

    private Properties properties ;

    private AdminClient adminClient;

    @Before
    public void init(){
        String path = new File("").getAbsolutePath() + "/src/test/resources/config.properties";
        properties = super.getProperties("config.properties");
        KafkaConsumerUtils.init(path);

        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getProperty("producer.bootstrap.servers"));
        props.put("enable.auto.commit", "true");
        props.put("client.id", "test");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        adminClient = AdminClient.create(props);
    }


    @Test
    public void testAdmin() throws Exception {

        DescribeClusterResult ds = adminClient.describeCluster();
        Collection<Node> nodes = ds.nodes().get();
        List<Integer> brokers = new ArrayList<>();
        List<String> top = new ArrayList<>();

        for(Node node : nodes) {
            System.out.println(node.host());
            brokers.add(node.id());
        }
        assertNotNull(ds);

        ListTopicsResult topics = adminClient.listTopics();
        Set<String> topicNames = topics.names().get();
        for(String topic: topicNames) {
            System.out.println(topic);
            top.add(topic);
        }
        assertNotNull(topics);

//        top.add("mobilegame_zxyd_topic");
//        top.add("mobilegame");

        DescribeTopicsResult topicsResult = adminClient.describeTopics(top);
        KafkaFuture<Map<String, TopicDescription>> mapKafkaFuture = topicsResult.all();
        Map<String, TopicDescription> tmp = mapKafkaFuture.get();

        for(Map.Entry<String,TopicDescription> entry : tmp.entrySet()){
            System.out.println(entry.getKey() + ": "+ entry.getValue());
        }


        DescribeLogDirsResult logDirsResult = adminClient.describeLogDirs(brokers);
        Map<Integer, KafkaFuture<Map<String, DescribeLogDirsResponse.LogDirInfo>>> vl = logDirsResult.values();
        for(Map.Entry<Integer, KafkaFuture<Map<String, DescribeLogDirsResponse.LogDirInfo>>> entry : vl.entrySet()){
            int id = entry.getKey();
            KafkaFuture<Map<String, DescribeLogDirsResponse.LogDirInfo>> value = entry.getValue();
            Map<String, DescribeLogDirsResponse.LogDirInfo> map = value.get(5, TimeUnit.MINUTES);

            for(Map.Entry<String, DescribeLogDirsResponse.LogDirInfo> m : map.entrySet()){
                String k = m.getKey();
                DescribeLogDirsResponse.LogDirInfo info = m.getValue();
                System.out.println(id + " : "+ k + " -> " + info.replicaInfos);
            }
        }

        System.out.println(" ------------------------------------> ");

        KafkaFuture<Map<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>>> future =  logDirsResult.all();
        Map<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>> map = future.get();

        for(Map.Entry<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>> entry : map.entrySet()){
            int id = entry.getKey();
            Map<String, DescribeLogDirsResponse.LogDirInfo> value = entry.getValue();
            for(Map.Entry<String, DescribeLogDirsResponse.LogDirInfo> m : value.entrySet()){
                String k = m.getKey();
                DescribeLogDirsResponse.LogDirInfo info = m.getValue();
                System.out.println(id + " : "+ k + " -> " + info.replicaInfos);
            }

        }
    }

//
//    @Test
//    public void testConsumer() throws Exception {
//        Consumer<String,String> consumer = KafkaConsumerUtils.consumer();
//        consumer.subscribe(Arrays.asList(properties.getProperty("consumer.topic").split(",")));
//
//        int count = 100;
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);
//            for (ConsumerRecord<String, String> record : records){
//                count -- ;
//                System.out.printf("offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value());
//                if(count<0){
//                    break;
//                }
//            }
//        }
//    }

    @Test
    public void testResetOffsetByTime() throws Exception {


    }

    @After
    public void close(){
        adminClient.close();
    }
}