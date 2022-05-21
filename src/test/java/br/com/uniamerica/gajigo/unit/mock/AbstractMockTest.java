package br.com.uniamerica.gajigo.unit.mock;

import br.com.uniamerica.gajigo.mock.AbstractMock;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Repeat;

@SpringBootTest
public abstract class AbstractMockTest<T> extends AbstractUnitTest {
    AbstractMock<T> mock;
    AbstractValidator<T> validator;

    public AbstractMockTest(AbstractMock<T> mock, AbstractValidator<T> validator) {
        this.mock = mock;
        this.validator = validator;
    }

    @Test
    @Repeat(100)
    public void testMockPassesValidation() throws Exception {
        T obj = mock.create(1).get(0);

        validator.validate(obj);
    }
}
