package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.validator.CountryValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountryValidatorTest extends AbstractValidatorTest<Country> {
    public CountryValidatorTest() {
        super(new CountryValidator(), Country.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Country country = new Country();
        Errors errors = getValidator().validate(country);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        Country country = validObject();
        country.setName("");

        Errors errors = getValidator().validate(country);

        assertEquals(1, errors.getErrorCount());
    }

    public Country validObject() {
        Country country = new Country("Testcountry");

        return country;
    }
}
