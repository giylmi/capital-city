package ru.kpfu.itis.capitalcity.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.kpfu.itis.capitalcity.model.Credentials;

import java.util.regex.Pattern;

/**
 * Created by a.gilmullin on 05.04.2016.
 */
@Component
public class CredentialsValidator extends Validator {

    private String validEmailRegex = "^[\\w\\.\\+\\-_]+@[\\w\\-]+\\.[\\w\\-\\.]{2,}$";

    @Override
    public boolean supports(Class<?> clazz) {
        return Credentials.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Credentials credentials = (Credentials) target;
        // todo: use message bundle instead hardcoded strings
        checkProperty("login", credentials.getLogin(), "Login is required", errors);
        checkProperty("password", credentials.getPassword(), "Password is required", errors);

        String email = credentials.getEmail();
        checkProperty("email", email, "Email is required!", errors);
        if (!Pattern.matches(validEmailRegex, email)) {
            errors.rejectValue("email", "", "Email should be valid!");
        }
    }
}
