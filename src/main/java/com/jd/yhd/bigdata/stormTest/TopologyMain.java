package com.jd.yhd.bigdata.stormTest;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import com.jd.yhd.bigdata.stormTest.bolts.WordCounter;
import com.jd.yhd.bigdata.stormTest.bolts.WordNormalizer;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.kafka.KafkaSpout;
import org.apache.storm.topology.TopologyBuilder;

public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("word-reader", new KafkaSpout(new BigDataConnection().getKafkaSpout("TEST-TOPIC-1")), 1);
        //builder.setSpout("word-reader",new WordReader(),1);
        builder.setBolt("word-normalizer", new WordNormalizer(), 1)
                .shuffleGrouping("word-reader");
        builder.setBolt("word-counter", new WordCounter(), 1)
                .shuffleGrouping("word-normalizer");
        //Configuration
		Config conf = new Config();
		conf.setDebug(false);
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
        conf.setMessageTimeoutSecs(30);

		String topologyName = "bigdata-storm-test";
        LocalCluster cluster = new LocalCluster();
		cluster.submitTopology(topologyName, conf, builder.createTopology());
		Thread.sleep(60000);
		cluster.shutdown();

//        try {
//			StormSubmitter.submitTopology(topologyName, conf, builder.createTopology());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
