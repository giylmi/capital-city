package ru.kpfu.itis.capitalcity.validator;

import org.springframework.validation.Errors;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
public abstract class Validator implements org.springframework.validation.Validator {
    protected Errors checkProperty(String property, String value, String msg, Errors errors) {
        if (value == null || value.isEmpty()) {
            errors.rejectValue(property, "", msg);
        }
        return errors;
    }
}
