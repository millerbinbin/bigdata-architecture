package com.jd.yhd.bigdata.mongo;

import com.jd.yhd.bigdata.commons.BigDataConnection;
import com.jd.yhd.bigdata.commons.MongoUtil;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import org.bson.Document;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author hubin6
 */
public class MongoStart {
    public static void main(String[] args) {
        try{
            MongoClient conn = new BigDataConnection().getMongoConnection();
            String tableName = "oneTime";
            MongoCollection<Document> collection = MongoUtil.getCollection(conn, tableName);
            Document filter = new Document().append("_id", "key_2");
            Document document = new Document().
                    append("_id", "key_2").
                    append("title", "MongoDB").
                    append("description", "database").
                    append("likes", 400).
                    append("by", "FlyTest").
                    append("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            MongoUtil.upsertRecord(collection, filter, document);
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
