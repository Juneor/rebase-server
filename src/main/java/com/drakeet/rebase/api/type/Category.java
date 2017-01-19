package com.drakeet.rebase.api.type;

/**
 * @author drakeet
 */
public class Category extends Jsonable {

    public final String key;
    public final String name;
    public final int rank;
    public String owner;


    public Category(String key, String name, int rank, String owner) {
        this.key = key;
        this.name = name;
        this.rank = rank;
        this.owner = owner;
    }
}
