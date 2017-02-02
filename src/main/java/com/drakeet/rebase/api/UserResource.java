package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Authorizations;
import com.drakeet.rebase.api.tool.Hashes;
import com.drakeet.rebase.api.tool.MongoDBs;
import com.drakeet.rebase.api.tool.Responses;
import com.drakeet.rebase.api.tool.URIs;
import com.drakeet.rebase.api.type.User;
import com.google.common.base.Optional;
import java.util.Date;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Projections.exclude;

/**
 * @author drakeet
 */
@Path("/users") public class UserResource {

    private static final String TAG = UserResource.class.getSimpleName();


    @Path("{username}")
    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response userDetail(@PathParam("username") String username) {
        Document _user = MongoDBs.users().find(eq(User.USERNAME, username))
            .projection(exclude(User.PASSWORD, User.AUTHORIZATION))
            .first();
        Optional<Document> user = Optional.fromNullable(_user);
        if (user.isPresent()) {
            return Response.ok(user.get()).build();
        } else {
            return Responses.notFound("The user is not found.");
        }
    }


    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        Document document = new Document(User.USERNAME, user.username)
            .append(User.PASSWORD, Hashes.sha1(user.password))
            .append(User.NAME, user.name)
            .append(User.EMAIL, user.email)
            .append(User.DESCRIPTION, user.description)
            .append(User.AUTHORIZATION, Authorizations.newInstance(user.username))
            .append(User.CREATED_AT, new Date());
        MongoDBs.users().insertOne(document);
        Document result = new Document(document);
        result.remove(User.PASSWORD);
        return Response.created(URIs.create("users", user.username))
            .entity(result)
            .build();
    }
}