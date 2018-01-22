package com.xiao.studydemos.kafka.consumer;

import com.xiao.studydemos.kafka.utils.KafkaClientsAdminUtils;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by MeiMei on 2018/1/7.
 */
public class AppStarter {

    public static void main(String[] args) throws IOException{
        String configFile = args[0].toString();
        InputStream inputStream = new FileInputStream(new File(configFile));
        Properties properties = new Properties();
        properties.load(inputStream);

        String topic = properties.getProperty("consumer.topic");
        List<String> topicList = Arrays.asList(topic.split(","));
        KafkaClientsAdminUtils clientAdminUtils = new KafkaClientsAdminUtils();
        clientAdminUtils.init(properties);
        Map<String, TopicDescription> topicMap = clientAdminUtils.describeTopics(topicList);
        ExecutorService pool = Executors.newFixedThreadPool(30);
        for(Map.Entry<String,TopicDescription> entry : topicMap.entrySet()){
            String topicName = entry.getKey();
            TopicDescription description = entry.getValue();
            List<TopicPartitionInfo> partitionInfoList = description.partitions();
            for(TopicPartitionInfo info : partitionInfoList){
                TopicPartition part = new TopicPartition(topicName, info.partition());
                pool.submit(new MultConsumer(properties,part));
            }
        }

    }
}
