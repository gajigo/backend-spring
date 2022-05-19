package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.State;
import org.springframework.validation.Errors;

public class CityValidator extends AbstractValidator<City> {
    public CityValidator() {
        super(City.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        City city = (City) obj;

        validateName(city, errors);
        validateState(city, errors);
    }

    private void validateName(City city, Errors errors) {
        String name = city.getName();
        if (!validateString("name", name, errors)) {
            return;
        }

        city.setName(capitalizeString(city.getName()));
    }

    private void validateState(City city, Errors errors) {
        State state = city.getState();
        validateNull("state", state,
                     "City must belong to a state!", errors);
    }
}
