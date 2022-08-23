package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Country;
import br.com.uniamerica.gajigo.entity.State;
import br.com.uniamerica.gajigo.validator.StateValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StateValidatorTest extends AbstractValidatorTest<State> {
    public StateValidatorTest() {
        super(new StateValidator(), State.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        State state = new State();
        Errors errors = getValidator().validate(state);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        State state = validObject();
        state.setName("");

        Errors errors = getValidator().validate(state);

        assertEquals(1, errors.getErrorCount());
    }

    public State validObject() {
        State state = new State("Teststate");
        Country country = new CountryValidatorTest().validObject();
        state.setCountry(country);

        return state;
    }
}
