package com.jd.yhd.bigdata.commons;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.storm.kafka.BrokerHosts;
import org.apache.storm.kafka.SpoutConfig;
import org.apache.storm.kafka.StringScheme;
import org.apache.storm.kafka.ZkHosts;
import org.apache.storm.spout.SchemeAsMultiScheme;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class BigDataConnection {
    private final static String KAFKA_SERVERS = Constants.KAFKA_SERVER();
    private final static String ZOOKEEPER_SERVERS = Constants.ZOOKEEPER_SERVER();

    public MongoClient getMongoConnection() {
        // only one node
        ServerAddress serverAddress = new ServerAddress(Constants.BIGDATA_HOST, Constants.MONGO_PORT);
        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress);

        //authentication
        MongoCredential credential = MongoCredential.createScramSha1Credential(Constants.MONGO_USERNAME, Constants.MONGO_DATABASE, Constants.MONGO_PASSWORD);
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs, credentials);
        return mongoClient;
    }

    public Jedis getRedisConnection() {
        Jedis jedis = new Jedis(Constants.BIGDATA_HOST, Constants.REDIS_PORT);
        return jedis;
    }

    public Producer<String, String> getKafkaProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVERS);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);
        return producer;
    }

    public KafkaConsumer<String, String> getKafkaConsumer(String group_id) {
        Properties props = new Properties();
        props.put("bootstrap.servers", KAFKA_SERVERS);
        props.put("group.id", group_id);
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        return consumer;
    }

    public SpoutConfig getKafkaSpout(String topic) {
        BrokerHosts brokerHosts = new ZkHosts(ZOOKEEPER_SERVERS);
        String spoutId = "kafka-storm-test-001";
        SpoutConfig spoutConfig = new SpoutConfig(brokerHosts, topic, "/consumers/" + spoutId, spoutId);
        spoutConfig.zkPort = Constants.ZOOKEEPER_PORTS;
        spoutConfig.zkServers = Arrays.asList(Constants.BIGDATA_HOST);
        spoutConfig.socketTimeoutMs = 60 * 1000;
        spoutConfig.scheme = new SchemeAsMultiScheme(new StringScheme());
        return spoutConfig;
    }

}
