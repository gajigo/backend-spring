package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class EventValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Event event = (Event) obj;

        checkNameNotBlank(event, errors);
    }

    private void checkNameNotBlank(Event event, Errors errors) {
        if (event.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Event name must not be empty!");
        }
    }
}
