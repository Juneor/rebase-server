package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Log;
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
class MongoJDBC {

    private static final String TAG = MongoJDBC.class.getSimpleName();
    private static MongoCollection<Document> userCollection;


    public static void setup() {
        try {
            MongoClient mongoClient = new MongoClient("localhost", 27017);

            MongoDatabase mongoDatabase = mongoClient.getDatabase("test");
            Log.i(TAG, "Connect to database successfully");
            userCollection = mongoDatabase.getCollection("user");
            if (userCollection == null) {
                mongoDatabase.createCollection("user");
                userCollection = mongoDatabase.getCollection("user");
            }
        } catch (Exception e) {
            Log.e(TAG, "[setup]", e);
        }
    }


    public static void insert(User user) {
        Document document = new Document("name", user.name);
        userCollection.insertOne(document);
        Log.i(TAG, "Insert user successfully");
    }


    public static Optional<User> find(String userName) {
        Document document = new Document("name", userName);
        FindIterable<Document> result =  userCollection.find(document);
        if (result == null) {
            return Optional.absent();
        }
        Document userDoc = result.limit(1).iterator().next();
        Log.i(TAG, "find user successfully" + userDoc.toJson());
        User user = new Gson().fromJson(userDoc.toJson(), User.class);
        return Optional.fromNullable(user);
    }
}