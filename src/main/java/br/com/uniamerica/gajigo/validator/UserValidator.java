package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.validation.Errors;

@Configurable
public class UserValidator extends AbstractValidator<User> {
    public UserValidator() {
        super(User.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        validateName(user, errors);
        validateEmail(user, errors);
        validateUsername(user, errors);
        validatePassword(user, errors);
    }

    private void validateName(User user, Errors errors) {
        String name = user.getName();
        validateString("name", name, errors);
    }

    private void validateEmail(User user, Errors errors) {
        String email = user.getEmail();
        if (!validateNull("email", email, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        if (!email.contains("@")) {
            errors.rejectValue("email", "email.invalid",
                    "Email must be valid!");
        }

        user.setEmail(user.getEmail().toLowerCase());
    }

    private void validateUsername(User user, Errors errors) {
        String username = user.getUsername();

        validateString("username", username, errors);
        if (username == null) {
            // Cannot do any more validations if the field is null
            return;
        }
    }

    private void validatePassword(User user, Errors errors) {
        String password = user.getPassword();
        validateString("password", password, errors);
    }
}
