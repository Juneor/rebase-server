package com.drakeet.rebase.api.type;

/**
 * @author drakeet
 */
public class Category extends Jsonable {

    public static final String KEY = "key";
    public static final String NAME = "name";
    public static final String RANK = "rank";
    public static final String OWNER = "owner";

    public String key;
    public String name;
    public int rank;
    public String owner;


    public Category() {
    }


    public Category(String key, String name, int rank, String owner) {
        this.key = key;
        this.name = name;
        this.rank = rank;
        this.owner = owner;
    }
}
