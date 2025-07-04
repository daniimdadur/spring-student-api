package com.imdadur.student_api.anotation.validator;

import com.imdadur.student_api.anotation.ValidGrade;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class GradeValidator implements ConstraintValidator<ValidGrade, String> {
    private static final Pattern GRADE_PATTERN = Pattern.compile("^[A-D][+-]?|F$");

    @Override
    public void initialize(ValidGrade constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        return GRADE_PATTERN.matcher(value).matches();
    }
}
