package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import br.com.uniamerica.gajigo.unit.AbstractUnitTest;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.StateValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class StateValidatorTest extends AbstractValidatorTest<State> {
    public StateValidatorTest() {
        super(new StateValidator(), State.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        State state = new State();
        Errors errors = validate(state);

        assert errors.hasErrors();
    }

    @Test
    public void testEmptyName() throws Exception {
        State state = validObject();
        state.setName("");

        Errors errors = validate(state);

        assert errors.getErrorCount() == 1;
    }

    public State validObject() {
        State state = new State("Teststate");
        Country country = new CountryValidatorTest().validObject();
        state.setCountry(country);

        return state;
    }
}
