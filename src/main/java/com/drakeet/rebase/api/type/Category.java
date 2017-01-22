package com.drakeet.rebase.api.type;

/**
 * @author drakeet
 */
public class Category extends Jsonable {

    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String RANK = "rank";
    public static final String OWNER = "owner";
    public static final String UPDATED_AT = "updated_at";
    public static final String CREATED_AT = "created_at";

    public String key;
    public String name;
    public int rank;
    public String owner;
}
