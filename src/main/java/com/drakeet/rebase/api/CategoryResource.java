package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Authorizations;
import com.drakeet.rebase.api.tool.Globals;
import com.drakeet.rebase.api.tool.MongoDBs;
import com.drakeet.rebase.api.tool.Responses;
import com.drakeet.rebase.api.tool.URIs;
import com.drakeet.rebase.api.type.Category;
import com.mongodb.MongoWriteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

import static com.drakeet.rebase.api.type.Category.CREATED_AT;
import static com.drakeet.rebase.api.type.Category.KEY;
import static com.drakeet.rebase.api.type.Category.NAME;
import static com.drakeet.rebase.api.type.Category.OWNER;
import static com.drakeet.rebase.api.type.Category.RANK;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.excludeId;
import static com.mongodb.client.model.Sorts.ascending;

/**
 * @author drakeet
 */
@Path("/categories") public class CategoryResource {

    @HeaderParam("Authorization") String auth;


    @Path("{owner}")
    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response readAllOf(@PathParam("owner") String owner) {
        List<Document> categories = new ArrayList<>();
        MongoDBs.categories().find()
            .projection(excludeId())
            .filter(eq(OWNER, owner))
            .sort(ascending(RANK))
            .limit(Globals.LIMIT_CATEGORIES)
            .into(categories);
        return Response.ok(categories).build();
    }


    @Path("{owner}")
    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response newCategory(Category input, @PathParam("owner") String owner) {
        Authorizations.verify(owner, auth);
        Document category = new Document(KEY, input.key)
            .append(NAME, input.name)
            .append(RANK, input.rank)
            .append(OWNER, owner)
            .append(CREATED_AT, new Date());
        try {
            MongoDBs.categories().insertOne(category);
        } catch (final MongoWriteException e) {
            return Responses.dbWriteError(e);
        }
        return Response.created(URIs.create("categories", owner, input.key))
            .entity(category)
            .build();
    }
}