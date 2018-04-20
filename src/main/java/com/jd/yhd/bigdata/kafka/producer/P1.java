package com.jd.yhd.bigdata.kafka.producer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author hubin6
 */
public class P1 {
    static int corePoolSize = 5;
    static int maximumPoolSize = 10;
    static long keepAliveTime = 200;
    static ArrayBlockingQueue<Runnable> workQueue = new ArrayBlockingQueue<>(5);

    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, TimeUnit.MINUTES, workQueue);
        threadPoolExecutor.execute(new KafkaProducerStart("p-1", "TEST-TOPIC-1"));
        // threadPoolExecutor.execute(new KafkaProducerStart("p-2", "TEST-TOPIC-1"));
        // threadPoolExecutor.execute(new KafkaProducerStart("p-3", "TEST-TOPIC-3"));
        // threadPoolExecutor.execute(new KafkaProducerStart("p-4", "TEST-TOPIC-4"));
    }
}
