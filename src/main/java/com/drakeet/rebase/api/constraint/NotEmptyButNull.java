package com.drakeet.rebase.api.constraint;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

/**
 * @author drakeet
 */
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { NotEmptyButNull.Validator.class })
public @interface NotEmptyButNull {

    String message() default "{com.drakeet.rebase.api.constraint.NotEmptyButNull.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<NotEmptyButNull, String> {
        @Override
        public void initialize(final NotEmptyButNull notEmptyButNull) {
        }


        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext context) {
            return value == null || !"".equals(value);
        }
    }
}
