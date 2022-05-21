package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.State;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.CityValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class CityValidatorTest extends AbstractValidatorTest<City> {
    public CityValidatorTest() {
        super(new CityValidator(), City.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        City city = new City();
        Errors errors = validator.validate(city);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        City city = validObject();
        city.setName("");

        Errors errors = validator.validate(city);

        assertEquals(1, errors.getErrorCount());
    }

    public City validObject() {
        City city = new City("Testcity");
        State state = new StateValidatorTest().validObject();
        city.setState(state);

        return city;
    }
}
