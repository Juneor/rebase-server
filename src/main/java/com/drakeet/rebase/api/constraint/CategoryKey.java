package com.drakeet.rebase.api.constraint;

import com.drakeet.rebase.api.tool.Globals;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author drakeet
 */
@Constraint(validatedBy = { CategoryKey.Validator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
public @interface CategoryKey {

    int min() default 1;

    int max() default Globals.SIZE_CATEGORY;

    String message() default "{org.hibernate.validator.constraints.Length.message}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<CategoryKey, String> {

        private int min, max;


        @Override
        public void initialize(final CategoryKey annotation) {
            this.min = annotation.min();
            this.max = annotation.max();
        }


        @Override
        public boolean isValid(final String value, final ConstraintValidatorContext context) {
            return value == null || (value.length() >= min && value.length() <= max);
        }
    }
}