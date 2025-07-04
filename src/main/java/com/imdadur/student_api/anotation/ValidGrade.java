package com.imdadur.student_api.anotation;

import com.imdadur.student_api.anotation.validator.GradeValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GradeValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidGrade {
    String message() default "invalid grade value, must be use A+ to D- or F";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
