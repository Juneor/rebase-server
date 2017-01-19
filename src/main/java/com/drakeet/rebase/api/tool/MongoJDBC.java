package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.User;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 * @author drakeet
 */
public class MongoJDBC {

    private static final String TAG = MongoJDBC.class.getSimpleName();
    private static MongoCollection<Document> userCollection;
    private static MongoDatabase db;


    public static void setUp() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);
            db = mongoClient.getDatabase("rebase");
            Log.i(TAG, "Connect to database successfully");
        } catch (Exception e) {
            Log.e(TAG, "[setup]", e);
        }
    }


    public static MongoDatabase db() {
        return db;
    }


    public static void insert(User user) {
        Document document = new Document("name", user.username);
        userCollection.insertOne(document);
        Log.i(TAG, "Insert user successfully");
    }


    public static Optional<User> find(String userName) {
        Document document = new Document("name", userName);
        FindIterable<Document> result = userCollection.find(document);
        if (result == null) {
            return Optional.absent();
        }
        Document userDoc = result.limit(1).iterator().next();
        Log.i(TAG, "find user successfully" + userDoc.toJson());
        User user = new Gson().fromJson(userDoc.toJson(), User.class);
        return Optional.fromNullable(user);
    }
}