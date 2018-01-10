package com.xiao.studydemos.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Created by xiaoliang on 2018/1/4.
 */
public class MultConsumer implements Runnable{
    public static final Logger logger = LoggerFactory.getLogger(MultConsumer.class);
    private Consumer consumer;

    public MultConsumer(Properties p,TopicPartition partition) {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, p.getProperty("consumer.bootstrap.servers"));
        props.put(ConsumerConfig.GROUP_ID_CONFIG, p.getProperty("consumer.group.id"));
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, p.getProperty("consumer.enable.auto.commit","false"));
        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, p.getProperty("consumer.auto.commit.interval.ms","100"));
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, p.getProperty("consumer.auto.offset.reset","latest"));
        props.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, p.getProperty("consumer.session.timeout.ms","10000"));
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, p.getProperty("consumer.key.deserializer"));
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, p.getProperty("consumer.value.deserializer"));
        if(consumer==null){
            consumer = new KafkaConsumer(props);
        }
        TopicPartition pa = new TopicPartition(partition.topic(), partition.partition());
        consumer.assign(Arrays.asList(pa));
    }

    @Override
    public void run() {
        int count = 0;
        while(true){
            ConsumerRecords<String, String> records = consumer.poll(10000);
            for (TopicPartition partition : records.partitions()) {
                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> record : partitionRecords) {
                    System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
                }
                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
            }
            count ++;
        }
    }
}


