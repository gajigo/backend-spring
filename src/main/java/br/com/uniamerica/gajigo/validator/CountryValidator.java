package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Country;
import org.springframework.validation.Errors;

public class CountryValidator extends AbstractValidator<Country> {
    public CountryValidator() {
        super(Country.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Country country = (Country) obj;

        validateName(country, errors);
    }

    private void validateName(Country country, Errors errors) {
        String name = country.getName();
        if (!validateString("name", name, errors)) {
            return;
        }

        country.setName(capitalizeString(country.getName()));
    }
}
