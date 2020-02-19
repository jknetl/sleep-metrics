package org.jknetl.sleepmetrics.config;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Duration;

public class PeriodValidator implements ConstraintValidator<DurationConstraint, Duration> {

    DurationConstraint constraint;
    @Override
    public void initialize(DurationConstraint constraintAnnotation) {
        this.constraint = constraintAnnotation;
    }

    @Override
    public boolean isValid(Duration value, ConstraintValidatorContext context) {
        long seconds = value.getSeconds();
        if (constraint.min() <= seconds && seconds <= constraint.max()) {
            return true;
        }

        return false;
    }
}
