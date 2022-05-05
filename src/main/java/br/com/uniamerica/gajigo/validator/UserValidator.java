package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.validation.Errors;

public class UserValidator extends AbstractValidator<User> {
    public UserValidator() {
        super(User.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        User user = (User) obj;

        validateName(user, errors);
        validateEmail(user, errors);
    }

    private void validateName(User user, Errors errors) {
        String name = user.getName();
        validateString("name", name, errors);
    }

    private void validateEmail(User user, Errors errors) {
        String email = user.getEmail();
        validateNull("email", email, errors);

        if (!email.contains("@")) {
            errors.rejectValue("email", "email.invalid",
                    "Email must be valid!");
        }
    }
}
