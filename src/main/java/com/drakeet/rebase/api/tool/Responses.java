package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Failure;
import com.mongodb.MongoWriteException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author drakeet
 */
public class Responses {

    public static Response dbWriteError(final MongoWriteException e) {
        return Response.status(e.getError().getCode())
            .entity(new Failure(e.getError().getMessage()))
            .build();
    }


    public static Response unauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED)
            .entity(Failures.error("Unauthorized"))
            .build();
    }


    public static Response notFound(String message) {
        return Response.status(NOT_FOUND)
            .entity(Failures.error(message))
            .build();
    }
}
