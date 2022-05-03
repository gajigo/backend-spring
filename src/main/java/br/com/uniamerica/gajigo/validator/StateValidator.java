package br.com.uniamerica.gajigo.validator;

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

        checkNameNotBlank(state, errors);
    }

    private void checkNameNotBlank(State state, Errors errors) {
        if (state.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "State name must not be empty!");
        }
    }
}
