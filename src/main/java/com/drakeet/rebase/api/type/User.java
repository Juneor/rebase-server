package com.drakeet.rebase.api.type;

import java.util.Date;
import org.bson.Document;

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
    public static final String AUTHORIZATION = "authorization";
    public static final String CREATED_AT = "created_at";

    public String username;
    public String password;
    public String name;
    public String email;
    public String description;

    public Authorization authorization;


    public User(String username) {
        this.username = username;
    }


    public static class Authorization extends Document {

        public String getAccessToken() {
            return getString("access_token");
        }


        public void setAccessToken(String accessToken) {
            put("access_token", accessToken);
        }


        public Date getUpdatedAt() {
            return getDate("updated_at");
        }


        public void setUpdatedAt(Date updatedAt) {
            put("updated_at", updatedAt);
        }
    }
}