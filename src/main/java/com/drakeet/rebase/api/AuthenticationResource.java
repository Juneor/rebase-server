package com.drakeet.rebase.api;

import com.drakeet.rebase.api.constraint.Username;
import com.drakeet.rebase.api.tool.Authorizations;
import com.drakeet.rebase.api.tool.Hashes;
import com.drakeet.rebase.api.tool.MongoDBs;
import com.drakeet.rebase.api.type.Failure;
import com.drakeet.rebase.api.type.User;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.hibernate.validator.constraints.NotEmpty;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static javax.ws.rs.core.Response.Status.FORBIDDEN;

/**
 * @author drakeet
 */
@Path("/authentications") public class AuthenticationResource {

    private static final String TAG = AuthenticationResource.class.getSimpleName();


    @GET @Path("{username}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response authorize(
        @Username @PathParam("username") String username,
        @NotEmpty @QueryParam("password") String password) {

        Bson filter = and(eq(User.USERNAME, username), eq(User.PASSWORD, Hashes.sha1(password)));
        Document newAuth = Authorizations.newInstance(username);
        Document user = MongoDBs.users().findOneAndUpdate(filter, set(User.AUTHORIZATION, newAuth));
        if (user == null) {
            return Response.status(FORBIDDEN)
                .entity(new Failure("The username or password is incorrect"))
                .build();
        } else {
            return Response.ok(newAuth).build();
        }
    }
}