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

        validateName(country, errors);
    }

    private void validateName(Country country, Errors errors) {
        String name = country.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "Country name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Country name must not be empty!");
        }
    }
}
