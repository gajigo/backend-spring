package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class UserValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        checkEmailValid(user, errors);
        checkNameNotBlank(user, errors);
    }

    private void checkEmailValid(User user, Errors errors) {
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "email.invalid",
                               "Email must be valid!");
        }
    }

    private void checkNameNotBlank(User user, Errors errors) {
        if (user.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "User name must not be empty!");
        }
    }
}
