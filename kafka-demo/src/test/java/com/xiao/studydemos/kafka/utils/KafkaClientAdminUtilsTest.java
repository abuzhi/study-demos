package com.xiao.studydemos.kafka.utils;

import com.xiao.studydemos.BaseTest;
import org.apache.kafka.clients.admin.DescribeLogDirsResult;
import org.apache.kafka.clients.admin.DescribeReplicaLogDirsResult;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.admin.TopicListing;
import org.apache.kafka.common.Node;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.apache.kafka.common.TopicPartitionReplica;
import org.apache.kafka.common.requests.DescribeLogDirsResponse;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

/**
 * Created by MeiMei on 2018/1/7.
 */
public class KafkaClientAdminUtilsTest extends BaseTest{

    private KafkaClientsAdminUtils clientAdminUtils;
    private Properties properties ;

    @Before
    public void init(){
        properties = super.getProperties("config.properties");
        Properties props = new Properties();
        props.put("bootstrap.servers", properties.getProperty("bootstrap.servers"));
        props.put("client.id","test");
        clientAdminUtils = new KafkaClientsAdminUtils();
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


    @Test
    public void describeLogDirs() throws Exception {
        Collection<Node> nodes = clientAdminUtils.describeCluster();

        Set<Integer> hosts = new HashSet<>();

        for(Node node : nodes){
            hosts.add(node.id());
            System.out.println(node.host() + " || "+ node.port() + " || "+ node.id() + " || "+ node.rack());
        }

        DescribeLogDirsResult result = clientAdminUtils.getAdminClient().describeLogDirs(hosts);
        Map<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>> map = result.all().get();

        for(Map.Entry<Integer, Map<String, DescribeLogDirsResponse.LogDirInfo>> entry : map.entrySet()){
            System.out.println(entry.getKey());
            Map<String, DescribeLogDirsResponse.LogDirInfo> logMap = entry.getValue();
            for(Map.Entry<String, DescribeLogDirsResponse.LogDirInfo> log : logMap.entrySet()){
                System.out.println(log.getKey());
                DescribeLogDirsResponse.LogDirInfo info = log.getValue();
                Map<TopicPartition, DescribeLogDirsResponse.ReplicaInfo> repMap = info.replicaInfos;
                System.out.println(repMap.toString());
            }
        }
    }

    @Test
    public void describeRepliaDirs() throws Exception {
        TopicPartitionReplica t0 = new TopicPartitionReplica("test_topic",0,190);
        TopicPartitionReplica t1 = new TopicPartitionReplica("test_topic",1,191);
        TopicPartitionReplica t2 = new TopicPartitionReplica("test_topic",2,192);
        Collection<TopicPartitionReplica> parts = new HashSet<>();
        parts.add(t0);
        parts.add(t1);
        parts.add(t2);

        DescribeReplicaLogDirsResult result = clientAdminUtils.getAdminClient().describeReplicaLogDirs(parts);
        Map<TopicPartitionReplica, DescribeReplicaLogDirsResult.ReplicaLogDirInfo> map = result.all().get();
        for(Map.Entry<TopicPartitionReplica, DescribeReplicaLogDirsResult.ReplicaLogDirInfo> entry :map.entrySet()){
            TopicPartitionReplica replica = entry.getKey();
            System.out.println(replica.brokerId() + " || "+ replica.partition() + " || "+ replica.topic());

            DescribeReplicaLogDirsResult.ReplicaLogDirInfo info = entry.getValue();

            System.out.println(info.getCurrentReplicaLogDir() + " || "+ info.getCurrentReplicaOffsetLag() + " || "+
                info.getFutureReplicaLogDir() + " || "+ info.getFutureReplicaOffsetLag());
        }

    }
}