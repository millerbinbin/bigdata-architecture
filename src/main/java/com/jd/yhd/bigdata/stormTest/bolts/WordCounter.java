package com.jd.yhd.bigdata.stormTest.bolts;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import com.jd.yhd.bigdata.commons.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Tuple;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class WordCounter extends BaseBasicBolt {

	Integer id;
	String name;
	Map<String, Integer> counters;
    MongoCollection<Document> collection;
	/**
	 * At the end of the spout (when the cluster is shutdown
	 * We will show the word counters
	 */
	@Override
	public void cleanup() {
		System.out.println("-- Word Counter ["+name+"-"+id+"] --");
		for(Map.Entry<String, Integer> entry : counters.entrySet()){
			System.out.println(entry.getKey()+": "+entry.getValue());
            Document filter = new Document().append("_id", entry.getKey());
            Document document = new Document().
                    append("_id", entry.getKey()).
                    append("count", entry.getValue()).
                    append("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            MongoUtil.upsertRecord(collection, filter, document);
		}
	}

	/**
	 * On create 
	 */
	@Override
	public void prepare(Map stormConf, TopologyContext context) {
		this.counters = new HashMap<String, Integer>();
		this.name = context.getThisComponentId();
		this.id = context.getThisTaskId();
        MongoClient conn = new BigDataConnection().getMongoConnection();
        this.collection = MongoUtil.getCollection(conn, "oneTime");
    }

	public void declareOutputFields(OutputFieldsDeclarer declarer) {}


	public void execute(Tuple input, BasicOutputCollector collector) {
		String str = input.getString(0);
		/**
		 * If the word dosn't exist in the map we will create
		 * this, if not We will add 1 
		 */
		if(!counters.containsKey(str)){
			counters.put(str, 1);
		}else{
			Integer c = counters.get(str) + 1;
			counters.put(str, c);
			if (c % 10 == 0){
				System.out.println(str+": "+c);
                Document filter = new Document().append("_id", str);
                Document document = new Document().
                        append("_id", str).
                        append("count", c).
                        append("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
                MongoUtil.upsertRecord(collection, filter, document);
			}
		}
	}
}
