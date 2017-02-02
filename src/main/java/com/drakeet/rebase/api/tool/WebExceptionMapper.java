package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Failure;
import com.mongodb.MongoWriteException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.Status.NOT_FOUND;

/**
 * @author drakeet
 */
@Provider
public class WebExceptionMapper implements ExceptionMapper<Exception> {

    private static final String TAG = WebExceptionMapper.class.getSimpleName();


    @Override
    public Response toResponse(final Exception exception) {
        Log.e(TAG, "[toResponse]" + exception.getMessage(), exception);
        if (exception instanceof WebApplicationException) {
            Response _response = ((WebApplicationException) exception).getResponse();
            return Response.fromResponse(_response)
                .entity(new Failure(_response.getStatusInfo().getReasonPhrase()))
                .type(MediaType.APPLICATION_JSON)
                .build();

        } else if (exception instanceof MongoWriteException) {
            MongoWriteException _exception = (MongoWriteException) exception;
            return Response.status(BAD_REQUEST)
                .entity(new Failure(_exception.getError().getMessage()))
                .build();

        } else if (exception instanceof NullPointerException) {
            return Response.status(NOT_FOUND)
                .entity(new Failure(exception.getMessage()))
                .build();

        } else {
            return Response.status(BAD_REQUEST)
                .entity(new Failure(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
    }
}
