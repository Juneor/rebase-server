package com.drakeet.rebase.api.type;

import com.google.gson.Gson;
import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class Category {

    public ObjectId _id;
    public final String key;
    public final String name;
    public final int rank;


    public Category(String key, String name, int rank) {
        this.key = key;
        this.name = name;
        this.rank = rank;
    }


    public String toJson() {
        return new Gson().toJson(this);
    }
}
