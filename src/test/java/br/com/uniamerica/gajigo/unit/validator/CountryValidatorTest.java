package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.CountryValidator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.ValidationErrors;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class CountryValidatorTest extends AbstractValidatorTest<Country> {
    public CountryValidatorTest() {
        super(new CountryValidator(), Country.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Country country = new Country();
        Errors errors = validator.validate(country);

        assert errors.hasErrors();
    }

    @Test
    public void testEmptyName() throws Exception {
        Country country = validObject();
        country.setName("");

        Errors errors = validator.validate(country);

        assert errors.getErrorCount() == 1;
    }

    public Country validObject() {
        Country country = new Country("Testcountry");

        return country;
    }
}
