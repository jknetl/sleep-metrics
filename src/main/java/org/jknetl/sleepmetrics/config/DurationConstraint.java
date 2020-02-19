package org.jknetl.sleepmetrics.config;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = PeriodValidator.class)
public @interface DurationConstraint {
    String message() default "Must be greater than {min} and lower than {max}";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
    long min() default Integer.MIN_VALUE;
    long max() default Integer.MAX_VALUE;
}
