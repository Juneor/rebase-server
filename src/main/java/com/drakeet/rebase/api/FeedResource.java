package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Authorizations;
import com.drakeet.rebase.api.tool.MongoJDBC;
import com.drakeet.rebase.api.tool.Responses;
import com.drakeet.rebase.api.tool.URIs;
import com.drakeet.rebase.api.type.Category;
import com.drakeet.rebase.api.type.Feed;
import com.mongodb.client.FindIterable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

import static com.drakeet.rebase.api.tool.ObjectIds.objectId;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.lt;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.descending;

/**
 * @author drakeet
 */
@Path("categories/{owner}/{category}/feeds") public class FeedResource {

    @PathParam("owner") String owner;
    @PathParam("category") String category;
    @HeaderParam("Authorization") String auth;


    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response readAll(
        @QueryParam("last_id") String lastId,
        @DefaultValue("20") @QueryParam("size") int size) {

        List<Document> feeds = new ArrayList<>();
        FindIterable<Document> iterable = MongoJDBC.feeds().find();
        if (lastId != null) {
            iterable.filter(lt(Feed._ID, objectId(lastId)));
        }
        iterable.projection(include(Feed._ID, Feed.TITLE, Feed.CONTENT,
            Feed.URL, Feed.CATEGORY, Feed.OWNER, Feed.COVER, Feed.PUBLISHED_AT))
            .sort(descending(Feed._ID))
            .filter(eq(Feed.CATEGORY, category))
            .filter(eq(Feed.OWNER, owner))
            .limit(size)
            .into(feeds);
        return Response.ok(feeds).build();
    }


    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response newFeed(Feed feed) {
        if (Authorizations.verify(owner, auth)) {
            if (!existCategory(category)) {
                return Responses.notFound("The category is not found.");
            }
            Document document = new Document(Feed.CATEGORY, category)
                .append(Feed.TITLE, feed.title)
                .append(Feed.CONTENT, feed.content)
                .append(Feed.URL, feed.url)
                .append(Feed.OWNER, owner)
                .append(Feed.PUBLISHED_AT, new Date());
            MongoJDBC.feeds().insertOne(document);
            return Response.created(
                URIs.create("categories", owner, category, "feeds",
                    document.getObjectId(Feed._ID).toHexString()))
                .entity(document)
                .build();
        } else {
            return Responses.unauthorized();
        }
    }


    private boolean existCategory(String key) {
        return MongoJDBC.categories().find(eq(Category.KEY, key))
            .projection(include(Category.KEY))
            .limit(1).first() != null;
    }
}
