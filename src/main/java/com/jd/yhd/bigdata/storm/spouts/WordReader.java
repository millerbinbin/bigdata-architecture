package com.jd.yhd.bigdata.storm.spouts;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.IRichSpout;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;
import org.apache.storm.utils.Time;

import java.util.Map;
import java.util.Random;

/**
 * @author hubin6
 */
public class WordReader extends BaseRichSpout implements IRichSpout {
    private SpoutOutputCollector collector;
    private boolean completed = false;
    private final Random r = new Random(System.currentTimeMillis());

    @Override
    public void ack(Object msgId) {
        System.out.println("OK:" + msgId);
    }

    @Override
    public void fail(Object msgId) {
        System.out.println("FAIL:" + msgId);
    }


    /**
     * The only thing that the methods will do It is emit each
     * file line
     */
    @Override
    public void nextTuple() {
        /**
         * The nextuple it is called forever, so if we have been readed the file
         * we will wait and then return
         */
        if (completed) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //Do nothing
            }
            return;
        }
        int messageNo = -1;
        int loopCnt = 10000;
        for (int i = 0; i < loopCnt; ++i) {
            messageNo = r.nextInt(20);
            String data = "key_" + messageNo;
            this.collector.emit(new Values(data), data);
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
    @Override
    public void open(Map conf, TopologyContext context,
                     SpoutOutputCollector collector) {
        this.collector = collector;
    }

    /**
     * Declare the output field "word"
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("line"));
    }
}
