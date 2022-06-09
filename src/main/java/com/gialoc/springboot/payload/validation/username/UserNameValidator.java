package com.gialoc.springboot.payload.validation.username;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserNameValidator implements ConstraintValidator<UserNameValidation, String> {

    @Override
    public void initialize(UserNameValidation constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(value);
            if (m.find()) // check special character
            return false;
        if (value.matches(".*([ \t]).*")) // check space character
            return false;
        return true;
    }
}
