package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Country;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CountryValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Country.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Country country = (Country) obj;

        checkNameNotBlank(country, errors);
    }

    private void checkNameNotBlank(Country country, Errors errors) {
        if (country.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Country name must not be empty!");
        }
    }
}
