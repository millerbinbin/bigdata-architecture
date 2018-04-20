package com.jd.yhd.bigdata.commons;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

/**
 * @author hubin6
 */
public class MongoUtil {


    public static MongoCollection<Document> getCollection(MongoClient mongoClient, String tableName){
        MongoCollection<Document> collection = mongoClient.getDatabase(Constants.MONGO_DATABASE).getCollection(tableName);
        return collection;
    }

    public static void upsertRecord(MongoCollection<Document> collection, Document query, Document data){
        collection.replaceOne(query, data, new UpdateOptions().upsert(true));
    }


}
