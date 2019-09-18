package github;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public abstract class Repository <T>{

    private MongoClient mongoClient;
    private static final JsonConverter jsonConverter = new JsonConverter();

    private final String databaseName;

    protected Repository(String databaseName) {
        MongoClientOptions.Builder options = MongoClientOptions.builder();
        options.socketKeepAlive(true);

        mongoClient = new MongoClient("localhost:27017", options.build());

        this.databaseName = databaseName;
    }

    public void save(T t){
        MongoCollection<Document> collection = getDocumentMongoCollection(t.getClass());
        Document document = Document.parse(jsonConverter.toJson(t));
        collection.insertOne(document);
    }

    public void removeAll(Class<T> clazz){
        MongoCollection<Document> collection = getDocumentMongoCollection(clazz);
        collection.deleteMany(Filters.where("1 == 1"));
    }

    public T findOne(Class<T> clazz){
        MongoCollection<Document> collection = getDocumentMongoCollection(clazz);
        MongoCursor<Document> mongoCursor = collection.find().cursor();

        if(mongoCursor.hasNext())
            return jsonConverter.fromJson(mongoCursor.next().toJson(), clazz);

        return null;
    }

    public List<T> findMany(Class<T> clazz){
        MongoCollection<Document> collection = getDocumentMongoCollection(clazz);
        MongoCursor<Document> mongoCursor = collection.find().cursor();

        List<T> list = new ArrayList<T>();
        while (mongoCursor.hasNext()){
            list.add(jsonConverter.fromJson(mongoCursor.next().toJson(), clazz));
        }

        return list;
    }

    private MongoDatabase getMongoDatabase() {
        return mongoClient.getDatabase(databaseName);
    }

    private MongoCollection<Document> getDocumentMongoCollection(Class clazz) {
        String collectionName = clazz.getSimpleName();
        MongoDatabase database = getMongoDatabase();

        MongoCollection<Document> collection = database.getCollection(collectionName);

        if (collection != null)
            return collection;

        database.createCollection(collectionName);
        return database.getCollection(collectionName);
    }

}
