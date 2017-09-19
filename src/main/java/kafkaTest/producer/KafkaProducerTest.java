package kafkaTest.producer;


import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;
import commons.Constants;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.PartitionInfo;
import org.apache.storm.utils.Time;

import java.util.Properties;

public class KafkaProducerTest {
    private final Producer<String, String> producer;
    public final static String TOPIC = "TEST-TOPIC";
    public final static String BOOTSTRAP_SERVERS = Constants.BOOTSTRAP_SERVER();

    private KafkaProducerTest() {
        Properties props = new Properties();
        props.put("bootstrap.servers", BOOTSTRAP_SERVERS);
        props.put("acks", "all");
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
                Time.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new KafkaProducerTest().produce();
    }
}

