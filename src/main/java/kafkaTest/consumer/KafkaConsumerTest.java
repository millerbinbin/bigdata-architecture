package kafkaTest.consumer;

import kafkaTest.producer.KafkaProducerTest;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

public class KafkaConsumerTest implements Runnable{
    private final KafkaConsumer<String, String> consumer;
    private final String group_id;
    private final String c_name;
    public KafkaConsumerTest(String cname, String groupId) {
        group_id = groupId;
        c_name = cname;
        Properties props = new Properties();
        props.put("bootstrap.servers", KafkaProducerTest.BOOTSTRAP_SERVERS);
        props.put("group.id", group_id);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(KafkaProducerTest.TOPIC));
    }

    void consume() {
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("name = %s, consumer_group = %s, offset = %d, key = %s, value = %s%n", c_name, group_id, record.offset(), record.key(), record.value());
            consumer.commitAsync();
        }
    }

    @Override
    public void run() {
        new KafkaConsumerTest(c_name, group_id).consume();
    }
}
