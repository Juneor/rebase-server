package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Failure;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author drakeet
 */
public class Responses {

    public static Response notFound(String detailReason) {
        return Response.status(NOT_FOUND)
            .entity(new Failure(detailReason))
            .build();
    }
}
