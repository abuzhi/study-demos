package com.xiao.studydemos.kafka.utils;

import com.xiao.studydemos.BaseTest;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartitionInfo;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by MeiMei on 2018/1/7.
 */
public class KafkaClientAdminUtilsTest extends BaseTest{

    private KafkaClientAdminUtils clientAdminUtils;
    private Properties properties ;

    @Before
    public void init(){
        properties = super.getProperties("config.properties");
        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getProperty("bootstrap.servers"));
        props.put("client.id","test");
        clientAdminUtils = new KafkaClientAdminUtils();
        clientAdminUtils.init(props);
    }


    @Test
    public void describeCluster() throws Exception {
        Collection<Node> nodes = clientAdminUtils.describeCluster();

        for(Node node : nodes){
            System.out.println(node.host() + " || "+ node.port() + " || "+ node.id() + " || "+ node.rack());
        }
    }

    @Test
    public void listTopics() throws Exception {
        Collection<TopicListing> list = clientAdminUtils.listTopics();
        for(TopicListing topicListing :list){
            System.out.println(topicListing.name() + " || "+ topicListing.isInternal());

        }
    }

    @Test
    public void describeTopics() throws Exception {

        String[] strings = {"test_topic"};
        Map<String, TopicDescription> map  = clientAdminUtils.describeTopics(Arrays.asList(strings));
        for(Map.Entry<String,TopicDescription> entry : map.entrySet()){
            System.out.println(entry.getKey());
            TopicDescription topicDescription = entry.getValue();
            System.out.println(topicDescription.name() + " || "+ topicDescription.partitions() + " || "+ topicDescription.isInternal());

            List<TopicPartitionInfo> list = topicDescription.partitions();
            for(TopicPartitionInfo info : list){
                System.out.println(info.partition() + " || "+ info.leader() + " || "+ info.replicas() + " || "+ info.isr());
            }
        }
    }



}