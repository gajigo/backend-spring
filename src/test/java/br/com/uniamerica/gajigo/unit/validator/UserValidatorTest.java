package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.UserValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class UserValidatorTest extends AbstractValidatorTest<User> {
    public UserValidatorTest() {
        super(new UserValidator(), User.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        User user = new User();
        Errors errors = validate(user);

        assert errors.hasErrors();
    }
}
