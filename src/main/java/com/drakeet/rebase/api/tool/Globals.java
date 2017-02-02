package com.drakeet.rebase.api.tool;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class Globals {

    public static final int LIMIT_CATEGORIES = 11;
    public static final String ENDPOINT = "https://api.drakeet.com/rebase";
    public static final int MAX_SIZE = 100;
    public static final int SIZE_USERNAME = 12;
    public static final int SIZE_CATEGORY = 64;


    public static Gson newGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(ObjectId.class, new ObjectIdSerializer())
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .setPrettyPrinting()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");     // ISO 8601
        return gsonBuilder.create();
    }
}
