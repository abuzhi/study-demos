//package com.xiao.studydemos.kafka.steams;
//
//import org.apache.kafka.common.serialization.Serdes;
//
//import java.util.Properties;
//import java.util.concurrent.CountDownLatch;
//import org.apache.kafka.common.serialization.Serdes;
//import org.apache.kafka.streams.KafkaStreams;
//import org.apache.kafka.streams.StreamsConfig;
//import org.apache.kafka.streams.kstream.KStreamBuilder;
//import org.junit.Test;
//
//import java.util.Properties;
//import java.util.concurrent.CountDownLatch;
//
//
//import static org.junit.Assert.*;
//
///**
// * Created by xiaoliang
// * 2018.01.10 10:03
// *
// * @Version 1.0
// */
//public class KafkaStreamConsumerTest {
//
//
//    public void main(String[] args) throws Exception {
//        Properties props = super.getProperties("config.properties");
//        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "streams-pipe");
//        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, props.getProperty("bootstrap.servers"));
//        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//
//        final KStreamBuilder builder = new KStreamBuilder();
//
//        builder.stream("streams-plaintext-input").to("streams-pipe-output");
//
//        final KafkaStreams streams = new KafkaStreams(builder, props);
//        final CountDownLatch latch = new CountDownLatch(1);
//
//        // attach shutdown handler to catch control-c
//        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
//            @Override
//            public void run() {
//                streams.close();
//                latch.countDown();
//            }
//        });
//
//        try {
//            streams.start();
//            latch.await();
//        } catch (Throwable e) {
//            System.exit(1);
//        }
//        System.exit(0);
//    }
//
//}