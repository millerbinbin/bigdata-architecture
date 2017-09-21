package com.jd.yhd.bigdata.kafkaTest.consumer;

public class C1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new KafkaConsumerTest("t-1", "kafka-storm-test-001", "TEST-TOPIC-1"));
        Thread t2 = new Thread(new KafkaConsumerTest("t-2", "jd-group1", "TEST-TOPIC-2"));
        Thread t3 = new Thread(new KafkaConsumerTest("t-3", "jd-group2", "TEST-TOPIC-3"));
        Thread t4 = new Thread(new KafkaConsumerTest("t-4", "jd-group2", "TEST-TOPIC-1"));
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}
