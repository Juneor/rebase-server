package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Config;
import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.MongoJDBC;
import com.drakeet.rebase.api.type.Category;
import com.drakeet.rebase.api.type.Result;
import com.mongodb.client.MongoCollection;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.bson.Document;

/**
 * @author drakeet
 */
@Path("/categories") public class CategoryResource {

    public MongoCollection<Document> collection;


    public CategoryResource() {
        collection = MongoJDBC.db().getCollection("category");
    }


    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response readAll() {
        List<Category> categories = new ArrayList<>();
        collection.find()
            .sort(new Document("rank", 1))
            .forEach((Consumer<Document>) document -> {
                categories.add(
                    new Category(
                        document.getString("key"),
                        document.getString("name"),
                        document.getInteger("rank")));
            });
        return Response.ok(categories).build();
    }


    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response newCategory(Category category, @HeaderParam("Authorization") String auth) {
        Log.i("[newCategory]", category.toJson() + " Authorization: " + auth);
        if (auth.equals(Config.AUTHORIZATION)) {
            collection.insertOne(Document.parse(category.toJson()));
            return Response.ok(Result.success()).build();
        } else {
            return Response.status(Status.UNAUTHORIZED)
                .entity(Result.failure("Unauthorized"))
                .build();
        }
    }
}