package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public abstract class AbstractValidatorTest<T> extends AbstractUnitTest {
    @Getter
    private AbstractValidator validator;

    @Getter
    private Class<T> clazz;

    @Getter
    private String objName;

    public AbstractValidatorTest(AbstractValidator validator, Class<T> clazz) {
        this.validator = validator;
        this.objName = clazz.getSimpleName();
        this.clazz = clazz;
    }

    @Test
    public void testSupports() throws Exception {
        assertTrue(validator.supports(clazz));
    }

    @Test
    public abstract void testEmptyObject() throws Exception;

    @Test
    public void testValidObject() throws Exception {
        Errors errors = validator.validate(validObject());

        assertFalse(errors.hasErrors());
    }

    public abstract T validObject();
}
