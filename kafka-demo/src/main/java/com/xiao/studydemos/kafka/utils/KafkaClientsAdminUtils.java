package com.xiao.studydemos.kafka.utils;

import org.apache.kafka.clients.admin.*;
import org.apache.kafka.common.KafkaFuture;
import org.apache.kafka.common.Node;

import java.util.*;
import java.util.concurrent.ExecutionException;

/**
 * Created by xiaoliang
 * 2017.12.15 14:13
 *
 * @Version 1.0
 */
public class KafkaClientsAdminUtils {
    private Properties properties ;
    private AdminClient adminClient;

    public void init(Properties props){
        this.properties = props;
        adminClient = AdminClient.create(props);
    }

    public AdminClient getAdminClient() {
        return adminClient;
    }

    public Collection<Node> describeCluster(){
        DescribeClusterResult result = adminClient.describeCluster();
        Collection<Node> nodes = null;
        try {
            nodes = result.nodes().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return nodes;
    }

    public Collection<TopicListing> listTopics(){
        ListTopicsResult topics = adminClient.listTopics();
        Collection<TopicListing> topicNames = null;
        try {
            topicNames = topics.listings().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return topicNames;
    }

    public Map<String, TopicDescription> describeTopics(List<String> top){
        DescribeTopicsResult topicsResult = adminClient.describeTopics(top);
        KafkaFuture<Map<String, TopicDescription>> mapKafkaFuture = topicsResult.all();
        Map<String, TopicDescription> tmp = null;
        try {
            tmp = mapKafkaFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return tmp;
    }


}
