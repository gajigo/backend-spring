package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.State;
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

    @Test
    public void testEmptyName() throws Exception {
        City city = validObject();
        city.setName("");

        Errors errors = validate(city);

        assert errors.getErrorCount() == 1;
    }

    public City validObject() {
        City city = new City("Testcity");
        State state = new StateValidatorTest().validObject();
        city.setState(state);

        return city;
    }
}
