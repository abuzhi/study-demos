package com.xiao.studydemos.kafka.consumer;

import com.xiao.studydemos.kafka.utils.KafkaConsumerUtils;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.OffsetAndMetadata;
import org.apache.kafka.common.TopicPartition;

import java.io.*;
import java.util.*;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by MeiMei on 2018/1/7.
 */
public class AppStarter {

    public static void main(String[] args) throws IOException{
        String configFile = args[0].toString();

        InputStream inputStream = new FileInputStream(new File(configFile));
        Properties properties = new Properties();
        properties.load(inputStream);

        int part = 3;
        ExecutorService pool = Executors.newFixedThreadPool(part);

        for(int i = 0;i< part;i++){
            KafkaToRdbConsumer consumer = new KafkaToRdbConsumer();
            Queue<List<ConsumerRecord<String, String>>> queue = new LinkedBlockingDeque<>();
            consumer.setQueue(queue);
            pool.submit(consumer);
        }

        String topic = properties.getProperty("consumer.topic");
        Consumer<String,String> consumer = KafkaConsumerUtils.consumer();
        consumer.subscribe(Arrays.asList(topic.split(",")));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.partitions();
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);

                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
            }
        }

    }
}
