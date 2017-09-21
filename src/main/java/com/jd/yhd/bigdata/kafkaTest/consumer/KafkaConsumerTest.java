package com.jd.yhd.bigdata.kafkaTest.consumer;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.Arrays;

public class KafkaConsumerTest implements Runnable{
    private final KafkaConsumer<String, String> consumer;
    private final String group_id;
    private final String c_name;
    private final String subscribe_topic;

    public KafkaConsumerTest(String cname, String groupId, String topic) {
        group_id = groupId;
        c_name = cname;
        subscribe_topic = topic;
        consumer = new BigDataConnection().getKafkaConsumer(group_id);
        consumer.subscribe(Arrays.asList(subscribe_topic));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("name = %s, consumer_group = %s, offset = %d, key = %s, value = %s%n", c_name, group_id, record.offset(), record.key(), record.value());
            consumer.commitAsync();
        }
    }
}
