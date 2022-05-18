package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public abstract class AbstractValidatorTest<T> extends AbstractUnitTest {
    AbstractValidator validator;
    Class<T> clazz;
    String objName;

    public AbstractValidatorTest(AbstractValidator validator, Class<T> clazz) {
        this.validator = validator;
        this.objName = clazz.getSimpleName();
        this.clazz = clazz;
    }

    @Test
    public void testSupports() throws Exception {
        assert validator.supports(clazz);
    }

    @Test
    public abstract void testEmptyObject() throws Exception;

    @Test
    public void testValidObject() throws Exception {
        Errors errors = validate(validObject());

        assert !errors.hasErrors();
    }

    public abstract T validObject();

    Errors getErrors(T obj) {
        return new BindException(obj, objName);
    }

    Errors validate(T obj) throws Exception {
        Errors errors = getErrors(obj);
        validator.validate(obj, errors);

        return errors;
    }
}
