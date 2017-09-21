package com.jd.yhd.bigdata.kafkaTest.producer;

public class P1 {

    public static void main(String[] args) {
        Thread t1 = new Thread(new KafkaProducerTest("p-1", "TEST-TOPIC-1"));
        Thread t2 = new Thread(new KafkaProducerTest("p-2", "TEST-TOPIC-1"));
        Thread t3 = new Thread(new KafkaProducerTest("p-3", "TEST-TOPIC-3"));
        //Thread t4 = new Thread(new KafkaProducerTest("p-4", "TEST-TOPIC-4"));
        t1.start();
        t2.start();
        //t3.start();
        //t4.start();
    }
}
