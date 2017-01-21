package com.drakeet.rebase.api.type;

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

    public String title;
    public String content;
    public String url;
    public String category;
    public String owner;
    public String cover;


    /**
     * {"title":"a title","content":"a content",
     * "url":"a url","category":"a category", "cover":"a cover"}
     */
    public static void main(String[] args) {
        Feed feed = new Feed();
        feed.title = "a title";
        feed.content = "a content";
        feed.url = "a url";
        feed.category = "a category";
        feed.owner = "an owner";
        feed.cover = "a cover";
        System.out.println(feed.toJson());
    }
}
