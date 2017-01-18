package com.drakeet.rebase.api;

import com.drakeet.rebase.api.tool.Log;
import com.drakeet.rebase.api.tool.MongoJDBC;
import com.drakeet.rebase.api.type.User;
import com.google.common.base.Optional;
import com.google.gson.Gson;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author drakeet
 */
@Path("/user") public class UserResource {

    @Path("{name}")
    @GET @Produces(MediaType.APPLICATION_JSON)
    public Response userDetail(@PathParam("name") String name) {
        Optional<User> result = MongoJDBC.find(name);
        if (result.isPresent()) {
            return Response.ok(result.get()).build();
        } else {
            return Response.status(NOT_FOUND).entity("user not found").build();
        }
    }


    @POST @Consumes(MediaType.APPLICATION_JSON)
    public Response register(User user) {
        Log.i("~~~~~~", new Gson().toJson(user));
        MongoJDBC.insert(user);
        return Response.ok("ok").build();
    }
}