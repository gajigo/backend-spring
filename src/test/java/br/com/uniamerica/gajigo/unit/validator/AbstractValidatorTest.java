package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public abstract class AbstractValidatorTest<T> extends AbstractUnitTest {
    AbstractValidator validator;
    String objName;

    public AbstractValidatorTest(AbstractValidator validator, Class<T> clazz) {
        this.validator = validator;
        this.objName = clazz.getSimpleName();
    }

    @Test
    public abstract void testEmptyObject() throws Exception;

    Errors getErrors(T obj) {
        return new BindException(obj, objName);
    }

    Errors validate(T obj) throws Exception {
        Errors errors = getErrors(obj);
        validator.validate(obj, errors);

        return errors;
    }
}
