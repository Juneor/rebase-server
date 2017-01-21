package com.drakeet.rebase.api.type;

import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class User {

    public ObjectId _id;
    public String username;
    public String password;
    public String name;
    public String email;


    public User(String username) {
        this.username = username;
    }
}