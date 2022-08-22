package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import org.springframework.validation.Errors;

public class StateValidator extends AbstractValidator<State> {
    public StateValidator() {
        super(State.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        State state = (State) obj;

        validateName(state, errors);
        validateCountry(state, errors);
    }

    private void validateName(State state, Errors errors) {
        String name = state.getName();
        if (!validateString("name", name, errors)) {
            return;
        }

        state.setName(capitalizeString(state.getName()));
    }

    private void validateCountry(State state, Errors errors) {
        Country country = state.getCountry();
        validateNull("country", country,
                "State must belong to a country!", errors);
    }
}
