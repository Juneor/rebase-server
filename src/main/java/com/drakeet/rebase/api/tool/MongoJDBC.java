package com.drakeet.rebase.api.tool;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

/**
 * @author drakeet
 */
public class MongoJDBC {

    private static final String TAG = MongoJDBC.class.getSimpleName();
    private static MongoDatabase db;


    public static void setUp() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        db = mongoClient.getDatabase("rebase");
        Log.i(TAG, "[setUp] Connect to database successfully");
        MongoJDBC.attemptCreateCollections(db);
    }


    private static void attemptCreateCollections(MongoDatabase db) {
        try {
            db.createCollection("user");
            db.createCollection("category");
            db.createCollection("feed");
        } catch (Exception e) {
            Log.w(TAG, "[attemptCreateCollections] " + e.getMessage());
        }
    }


    public static MongoDatabase db() {
        return db;
    }
}