package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.CityValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class CityValidatorTest extends AbstractValidatorTest<City> {
    public CityValidatorTest() {
        super(new CityValidator(), City.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        City city = new City();
        Errors errors = validate(city);

        assert errors.hasErrors();
    }
}
