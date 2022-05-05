package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.State;
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

        validateName(city, errors);
        validateState(city, errors);
    }

    private void validateName(City city, Errors errors) {
        String name = city.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "City name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "City name must not be empty!");
        }
    }

    private void validateState(City city, Errors errors) {
        State state = city.getState();

        if (state == null) {
            errors.rejectValue("state", "state.null",
                               "City must belong to a state!");
        }
    }
}
