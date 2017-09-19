package mongoTest;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.UpdateOptions;
import commons.Constants;
import commons.MongoUtil;
import org.bson.Document;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MongoTest {
    public static void main( String args[] ){
        try{
            MongoClient mongoClient = new MongoUtil().getMongoConnection();
            String databaseName = Constants.MONGO_DATABASE;
            String tableName = "oneTime";
            MongoCollection<Document> collection = mongoClient.getDatabase(databaseName).getCollection(tableName);
            Document filter = new Document().append("_id", "key_2");
            Document document = new Document().
                    append("_id", "key_2").
                    append("title", "MongoDB").
                    append("description", "database").
                    append("likes", 400).
                    append("by", "FlyTest").
                    append("timestamp", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            collection.replaceOne(filter, document, new UpdateOptions().upsert(true));
        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}
