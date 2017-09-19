package commons;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class MongoUtil {
    public static MongoClient getMongoConnection(){
        // only one node
        ServerAddress serverAddress = new ServerAddress(Constants.BIGDATA_HOST , Constants.MONGO_PORT);
        List<ServerAddress> addrs = new ArrayList<>();
        addrs.add(serverAddress);

        //authentication
        MongoCredential credential = MongoCredential.createScramSha1Credential(Constants.MONGO_USERNAME, Constants.MONGO_DATABASE, Constants.MONGO_PASSWORD);
        List<MongoCredential> credentials = new ArrayList<>();
        credentials.add(credential);

        //通过连接认证获取MongoDB连接
        MongoClient mongoClient = new MongoClient(addrs,credentials);
        return mongoClient;
    }

    public static MongoCollection<Document> getCollection(MongoClient mongoClient, String tableName){
        MongoCollection<Document> collection = mongoClient.getDatabase(Constants.MONGO_DATABASE).getCollection(tableName);
        return collection;
    }

    public static void upsertRecord(MongoCollection<Document> collection, Document query, Document data){
        collection.replaceOne(query, data, new UpdateOptions().upsert(true));
    }



}
