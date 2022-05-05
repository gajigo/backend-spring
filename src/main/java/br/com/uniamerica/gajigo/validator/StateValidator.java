package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class StateValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return State.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        State state = (State) obj;

        validateName(state, errors);
        validateCountry(state, errors);
    }

    private void validateName(State state, Errors errors) {
        String name = state.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "State name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "State name must not be empty!");
        }
    }

    private void validateCountry(State state, Errors errors) {
        Country country = state.getCountry();

        if (country == null) {
            errors.rejectValue("country", "country.null",
                               "State must belong to a country!");
        }
    }
}
