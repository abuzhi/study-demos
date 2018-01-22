package com.xiao.studydemos.kafka;

import com.xiao.studydemos.BaseTest;
import com.xiao.studydemos.kafka.utils.KafkaClientsAdminUtils;
import com.xiao.studydemos.kafka.utils.KafkaConsumerUtils;
import org.apache.kafka.clients.admin.TopicDescription;
import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.PartitionInfo;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.TopicPartitionInfo;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by xiaoliang
 * 2017.12.14 17:18
 *
 * @Version 1.0
 */
public class KafkaConsumerUtilsTest extends BaseTest{

    private Properties properties ;

    @Before
    public void init(){
        String path = new File("").getAbsolutePath() + "/src/test/resources/config.properties";
        properties = super.getProperties("config.properties");
        KafkaConsumerUtils.init(path);
    }

    @Test
    public void consumer() throws Exception {
        String topic = properties.getProperty("consumer.topic");
        Consumer<String,String> consumer = KafkaConsumerUtils.consumer();
        List<String> topicList = Arrays.asList(topic.split(","));
        consumer.subscribe(topicList);


        int count = 100;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            records.partitions();

            for (TopicPartition partition : records.partitions()) {

                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                for (ConsumerRecord<String, String> record : partitionRecords) {
                    System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
                }

                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
            }
//
//            for (ConsumerRecord<String, String> record : records){
//                count -- ;
//                System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
//                if(count<0){
//                    break;
//                }
//            }
        }
    }


    @Test
    public void testOffset() throws Exception {
        Consumer<String,String> consumer = KafkaConsumerUtils.consumer();

        Map<String, List<PartitionInfo>> map =  consumer.listTopics();
        for(Map.Entry<String,List<PartitionInfo>> entry : map.entrySet()){
            System.out.println(entry.getKey() + ": "+ entry.getValue());
        }


    }

    @Test
    public void testConsumerPartitions() throws Exception {
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
                pool.submit(new ConsumerPart(properties,part));
            }
        }
//        int count = 100;
//        while (true) {
//            ConsumerRecords<String, String> records = consumer.poll(100);
//            records.partitions();
//
//            for (TopicPartition partition : records.partitions()) {
//
//                List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
//                for (ConsumerRecord<String, String> record : partitionRecords) {
//                    System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
//                }
//
//                long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
//                consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
//            }
//
//
//        }
    }

    private class ConsumerPart implements Runnable{
        private Consumer consumer;
        public ConsumerPart(Properties p,TopicPartition partition) {
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
            int count = 100;

            while(count > 0){
                ConsumerRecords<String, String> records = consumer.poll(10000);
                for (TopicPartition partition : records.partitions()) {
                    List<ConsumerRecord<String, String>> partitionRecords = records.records(partition);
                    for (ConsumerRecord<String, String> record : partitionRecords) {
                        System.out.printf("part=%d ,offset = %d, key = %s, value = %s%n ",record.partition(), record.offset(), record.key(), record.value());
                    }
                    long lastOffset = partitionRecords.get(partitionRecords.size() - 1).offset();
                    consumer.commitSync(Collections.singletonMap(partition, new OffsetAndMetadata(lastOffset + 1)));
                }
                count --;
            }
        }
    }


}

