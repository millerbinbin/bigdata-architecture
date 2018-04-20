package com.jd.yhd.bigdata.kafka.producer;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.storm.utils.Time;


/**
 * @author hubin6
 */
public class KafkaProducerStart implements Runnable {
    private final Producer<String, String> producer;
    private final String topic;
    private final String producerName;

    public KafkaProducerStart(String producerName, String topic) {
        this.producerName = producerName;
        this.topic = topic;
        producer = new BigDataConnection().getKafkaProducer();
    }

    @Override
    public void run() {
        int messageNo = 1000;
        final int count = 2000;
        System.out.println(producer.partitionsFor(topic));
        while (messageNo < count) {
            String key = String.valueOf(messageNo);
            String data = String.format("kafka message No.%d send by %s", messageNo, producerName);
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

