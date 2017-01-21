package com.drakeet.rebase.api.type;

/**
 * @author drakeet
 */
public class User {

    public static final String _ID = "_id";
    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String DESCRIPTION = "description";
    public static final String CREATED_AT = "created_at";

    public String username;
    public String password;
    public String name;
    public String email;
    public String description;


    public User(String username) {
        this.username = username;
    }
}