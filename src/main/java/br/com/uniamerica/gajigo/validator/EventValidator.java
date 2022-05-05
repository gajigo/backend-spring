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

        validateName(event, errors);
    }

    private void validateName(Event event, Errors errors) {
        String name = event.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "Event name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Event name must not be empty!");
        }
    }
}
