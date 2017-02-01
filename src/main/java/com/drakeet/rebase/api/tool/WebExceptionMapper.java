package com.drakeet.rebase.api.tool;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

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
                .entity(Failures.error(_response.getStatusInfo().getReasonPhrase()))
                .type(MediaType.APPLICATION_JSON)
                .build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity(Failures.error(exception.getMessage()))
                .type(MediaType.APPLICATION_JSON)
                .build();
        }
    }
}
