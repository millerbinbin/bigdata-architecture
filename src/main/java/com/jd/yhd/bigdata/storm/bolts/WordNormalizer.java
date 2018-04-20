package com.jd.yhd.bigdata.storm.bolts;


import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

/**
 * @author hubin6
 */
public class WordNormalizer extends BaseBasicBolt {

    /**
     * The bolt will receive the line from the
     * words file and process it to Normalize this line
     * <p>
     * The normalize will be put the words in lower case
     * and split the line to get all words in this
     */
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String sentence = input.getString(0);
        String[] words = sentence.split(",");
        for (String word : words) {
            word = word.trim();
            System.out.println(word);
            if (word != "") {
                word = word.toLowerCase();
                collector.emit(new Values(word));
            }
        }
    }


    /**
     * The bolt will only emit the field "word"
     */
    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("word"));
    }
}
