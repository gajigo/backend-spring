package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.validator.EventValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class EventValidatorTest extends AbstractValidatorTest<Event> {
    public EventValidatorTest() {
        super(new EventValidator(), Event.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Event event = new Event();
        Errors errors = validate(event);

        assert errors.hasErrors();
    }
}
