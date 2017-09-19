package stormTest.spouts;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Map;
import java.util.Random;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Time;

public class WordReader extends BaseRichSpout implements IRichSpout {

	private SpoutOutputCollector collector;
	private boolean completed = false;
	public void ack(Object msgId) {
		System.out.println("OK:"+msgId);
	}
	public void close() {}
	public void fail(Object msgId) {
		System.out.println("FAIL:"+msgId);
	}
	private final Random r = new Random(System.currentTimeMillis());
	/**
	 * The only thing that the methods will do It is emit each 
	 * file line
	 */
	public void nextTuple() {
		/**
		 * The nextuple it is called forever, so if we have been readed the file
		 * we will wait and then return
		 */
		if(completed){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				//Do nothing
			}
			return;
		}
		int messageNo = -1;
		for(int i = 0; i < 10000; ++i) {
			messageNo = r.nextInt(20);
			String data = "key_" + messageNo;
			//System.out.println(data);
			this.collector.emit(new Values(data),data);
			try {
				Time.sleep(3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		completed = true;
	}

	/**
	 * We will create the file and get the collector object
	 */
	public void open(Map conf, TopologyContext context,
			SpoutOutputCollector collector) {
		this.collector = collector;
	}

	/**
	 * Declare the output field "word"
	 */
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		declarer.declare(new Fields("line"));
	}
}
