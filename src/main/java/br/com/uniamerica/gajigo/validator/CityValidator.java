package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.City;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class CityValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return City.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        City city = (City) obj;

        checkNameNotBlank(city, errors);
    }

    private void checkNameNotBlank(City city, Errors errors) {
        if (city.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "City name must not be empty!");
        }
    }
}
