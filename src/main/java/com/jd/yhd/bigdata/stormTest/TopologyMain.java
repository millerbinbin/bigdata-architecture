package com.jd.yhd.bigdata.stormTest;

import com.jd.yhd.bigdata.stormTest.bolts.WordCounter;
import com.jd.yhd.bigdata.stormTest.bolts.WordNormalizer;
import com.jd.yhd.bigdata.stormTest.spouts.WordReader;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;


public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader(),2);
		builder.setBolt("word-normalizer", new WordNormalizer(),2)
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(),2)
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
