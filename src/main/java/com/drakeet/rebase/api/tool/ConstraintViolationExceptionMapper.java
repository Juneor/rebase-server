package com.drakeet.rebase.api.tool;

import com.drakeet.rebase.api.type.Failure;
import com.google.common.base.Joiner;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Priority;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Priorities;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

/**
 * @author drakeet
 */
@Provider
@Priority(Priorities.USER)
public class ConstraintViolationExceptionMapper
    implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(final ConstraintViolationException exception) {
        List<String> violations = new ArrayList<>();
        exception.getConstraintViolations()
            .stream()
            .forEach(input -> violations.add(format(input)));
        Joiner joiner = Joiner.on("; ").skipNulls();
        String detail = joiner.join(violations);
        return Response.status(BAD_REQUEST)
            .entity(new Failure(detail))
            .build();
    }


    public String format(ConstraintViolation<?> violation) {
        return String.format("%s = %s: %s",
            violation.getPropertyPath(),
            violation.getInvalidValue(),
            violation.getMessage());
    }
}
