package com.jd.yhd.bigdata.kafkaTest.producer;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.storm.utils.Time;


public class KafkaProducerTest implements Runnable {
    private final Producer<String, String> producer;
    private final String topic;
    private final String p_name;

    public KafkaProducerTest(String p_name, String topic) {
        this.p_name = p_name;
        this.topic = topic;
        producer = new BigDataConnection().getKafkaProducer();
    }

    @Override
    public void run() {
        int messageNo = 1000;
        final int COUNT = 2000;
        System.out.println(producer.partitionsFor(topic));
        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = String.format("kafka message No.%d send by %s", messageNo, p_name);
            producer.send(new ProducerRecord<String, String>(topic, key, data));
            System.out.println(data);
            messageNo++;
            try {
                Time.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

