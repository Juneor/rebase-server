package com.drakeet.rebase.api.type;

import java.util.Date;
import org.bson.types.ObjectId;

/**
 * @author drakeet
 */
public class Feed extends Jsonable {

    public ObjectId _id;
    public String title;
    public String content;
    public String url;
    public String category;
    public String owner;
    public String cover;
    public Date publishedAt;


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
