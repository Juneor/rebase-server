package com.drakeet.rebase.api.tool;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 * @author drakeet
 */
public class Resource {

    public final MongoCollection<Document> collection;


    public Resource(String collectionName) {
        collection = MongoJDBC.db().getCollection(collectionName);
    }
}
