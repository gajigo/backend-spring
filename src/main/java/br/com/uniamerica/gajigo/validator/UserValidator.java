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

        validateEmail(user, errors);
        validateName(user, errors);
    }

    private void validateEmail(User user, Errors errors) {
        if (!user.getEmail().contains("@")) {
            errors.rejectValue("email", "email.invalid",
                               "Email must be valid!");
        }
    }

    private void validateName(User user, Errors errors) {
        String name = user.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "User name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "User name must not be empty!");
        }
    }
}
