package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.UserValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class UserValidatorTest extends AbstractValidatorTest<User> {
    public UserValidatorTest() {
        super(new UserValidator(), User.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        User user = new User();
        Errors errors = validator.validate(user);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        User user = validObject();
        user.setName("");

        Errors errors = validator.validate(user);

        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void testInvalidEmail() throws Exception {
        User user = validObject();
        user.setEmail("invalid");

        Errors errors = validator.validate(user);

        assertEquals(1, errors.getErrorCount());
    }

    public User validObject() {
        User user = new User("test", "test",
                "test", "test",
                "test@fake.com", true);

        return user;
    }
}
