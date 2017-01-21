package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.Resource;
import com.drakeet.rebase.api.type.User;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import com.mongodb.client.FindIterable;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author drakeet
 */
@Path("/users") public class UserResource extends Resource {

    private static final String TAG = UserResource.class.getSimpleName();


    public UserResource() {
        super("user");
    }


    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response userDetail(@QueryParam("name") String name) {
        Optional<User> result = find(name);
        if (result.isPresent()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(NOT_FOUND).entity("user not found").build();
        }
    }


    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        Log.i("[register]", new Gson().toJson(user));
        insert(user);
        return Response.ok("ok").build();
    }


    public void insert(User user) {
        Document document = new Document("name", user.username);
        collection.insertOne(document);
        Log.i(TAG, "Insert user successfully");
    }


    public Optional<User> find(String userName) {
        Document document = new Document("name", userName);
        FindIterable<Document> result = collection.find(document);
        if (result == null) {
            return Optional.absent();
        }
        Document userDoc = result.limit(1).iterator().next();
        Log.i(TAG, "find user successfully" + userDoc.toJson());
        User user = new Gson().fromJson(userDoc.toJson(), User.class);
        return Optional.fromNullable(user);
    }
}