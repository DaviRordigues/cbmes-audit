package br.es.gov.cb.cbmesaudit.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DateRangeValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidDateRange {
    String message() default "Invalid date range: startDate must be before endDate and both must not be null.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
