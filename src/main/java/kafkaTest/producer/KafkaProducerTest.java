package kafkaTest.producer;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.storm.utils.Time;

import java.util.Properties;

public class KafkaProducerTest {
    private final Producer<String, String> producer;
    public final static String TOPIC = "TEST-TOPIC";
    public final static String ADVERTISED_HOST = "192.168.99.101";
    public final static String BOOTSTRAP_SERVERS = String.format("%s:32789, %s:32790", ADVERTISED_HOST, ADVERTISED_HOST);

    private KafkaProducerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("acks", "-1");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        producer = new KafkaProducer<>(props);
    }

    void produce() {
        int messageNo = 1000;
        final int COUNT = 2000;
        System.out.println(producer.partitionsFor(TOPIC));
        while (messageNo < COUNT) {
            String key = String.valueOf(messageNo);
            String data = "hello kafka message " + key;
            producer.send(new ProducerRecord<String, String>(TOPIC, key, data));
            System.out.println(data);
            messageNo++;
            try {
                Time.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new KafkaProducerTest().produce();
    }
}

