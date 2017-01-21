package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Authorizations;
import com.drakeet.rebase.api.tool.Config;
import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.Resource;
import com.drakeet.rebase.api.tool.URIs;
import com.drakeet.rebase.api.type.Category;
import com.drakeet.rebase.api.type.Result;
import com.drakeet.rebase.api.type.User;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.bson.Document;

import static com.drakeet.rebase.api.type.Category.KEY;
import static com.drakeet.rebase.api.type.Category.NAME;
import static com.drakeet.rebase.api.type.Category.OWNER;
import static com.drakeet.rebase.api.type.Category.RANK;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.include;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * @author drakeet
 */
@Path("/categories") public class CategoryResource extends Resource {

    public CategoryResource() {
        super("category");
    }


    @Path("{owner}")
    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response readAllOf(@PathParam("owner") String owner) {
        List<Document> categories = new ArrayList<>();
        collection.find()
            .projection(include(KEY, NAME, RANK, OWNER))
            .filter(eq(OWNER, owner))
            .sort(ascending(RANK))
            .limit(Config.LIMIT_CATEGORIES)
            .forEach((Consumer<Document>) categories::add);
        return Response.ok(categories).build();
    }


    @Path("{owner}")
    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response newCategory(
        Category category,
        @HeaderParam("Authorization") String auth,
        @PathParam("owner") String owner) {

        Log.i("[newCategory]", category.toJson() + " Authorization: " + auth);
        final User user = Authorizations.verify(owner, auth);
        if (user != null) {
            // TODO: 2017/1/19 check auth/key
            Document document = new Document(KEY, category.key)
                .append(NAME, category.name)
                .append(RANK, category.rank)
                .append(OWNER, user.username);
            collection.insertOne(document);
            return Response.created(URIs.create("categories", owner, category.key))
                .entity(document)
                .build();
        } else {
            return Response.status(Status.UNAUTHORIZED)
                .entity(Result.failure("Unauthorized"))
                .build();
        }
    }
}