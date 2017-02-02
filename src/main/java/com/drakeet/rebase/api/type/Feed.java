package com.drakeet.rebase.api.type;

import org.hibernate.validator.constraints.Length;

/**
 * @author drakeet
 */
public class Feed extends Jsonable {

    public static final String _ID = "_id";
    public static final String TITLE = "title";
    public static final String CONTENT = "content";
    public static final String URL = "url";
    public static final String CATEGORY = "category";
    public static final String OWNER = "owner";
    public static final String COVER = "cover";
    public static final String PUBLISHED_AT = "published_at";

    @Length(min = 1, max = 256)
    public String title;

    @Length(min = 1, max = 1024 * 1024)
    public String content;

    @org.hibernate.validator.constraints.URL
    public String url;

    @org.hibernate.validator.constraints.URL
    public String cover;
}
