package com.jd.yhd.bigdata.kafka.consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hubin6
 */
public class C1 {
    static int corePoolSize = 5;
    static int maximumPoolSize = 10;
    static long keepAliveTime = 200;
    static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
        threadPoolExecutor.execute(new KafkaConsumerStart("t-1", "kafka-storm-test-001", "TEST-TOPIC-1"));
        threadPoolExecutor.execute(new KafkaConsumerStart("t-2", "kafka-storm-test-001", "TEST-TOPIC-1"));
//        threadPoolExecutor.execute(new KafkaConsumerStart("t-3", "jd-group2", "TEST-TOPIC-3"));
//        threadPoolExecutor.execute(new KafkaConsumerStart("t-4", "jd-group2", "TEST-TOPIC-1"));
    }
}
