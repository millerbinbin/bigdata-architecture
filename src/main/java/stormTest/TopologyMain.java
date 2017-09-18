package stormTest;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import stormTest.spouts.WordReader;
import stormTest.bolts.WordCounter;
import stormTest.bolts.WordNormalizer;


public class TopologyMain {
	public static void main(String[] args) throws InterruptedException {
		TopologyBuilder builder = new TopologyBuilder();
		builder.setSpout("word-reader",new WordReader(),1);
		builder.setBolt("word-normalizer", new WordNormalizer(),2)
			.shuffleGrouping("word-reader");
		builder.setBolt("word-counter", new WordCounter(),1)
			.shuffleGrouping("word-normalizer");
		
        //Configuration
		Config conf = new Config();
		conf.put("wordsFile", "d:\\test2.txt");
		conf.setDebug(false);
		conf.setNumWorkers(2);
        //Topology run
		conf.put(Config.TOPOLOGY_MAX_SPOUT_PENDING, 1);
		LocalCluster cluster = new LocalCluster();
		cluster.submitTopology("Getting-Started-Toplogie", conf, builder.createTopology());
		Thread.sleep(10000);
		cluster.shutdown();
	}
}
