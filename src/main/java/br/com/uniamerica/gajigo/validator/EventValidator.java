package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

public class EventValidator extends AbstractValidator<Event> {
    public EventValidator() {
        super(Event.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Event event = (Event) obj;

        validateName(event, errors);
        validateAttendanceMode(event, errors);
        validateDate(event, errors);
    }

    private void validateName(Event event, Errors errors) {
        String name = event.getName();
        validateString("name", name, errors);
    }

    private void validateAttendanceMode(Event event, Errors errors) {
        AttendanceMode mode = event.getAttendanceMode();
        if (validateNull("attendanceMode", mode, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        // Se o evento for online, nao pode ter uma localizacao
        // se for misto ou offline, precisa ter
        if (event.getAttendanceMode() == AttendanceMode.Online) {
            event.setLocation(null);
        } else if (event.getLocation() == null){
            errors.rejectValue("location", "location.null",
                               "Event location cannot be null if " +
                                              "Attendance is Mixed or Offline!");
        }
    }

    private void validateDate(Event event, Errors errors) {
        LocalDateTime start = event.getStartDate();
        LocalDateTime end = event.getEndDate();

        // TODO remove special logic for null, you shouldn't be able to schedule an event
        // TODO ... without, you know, scheduling it...
        if (end != null && end.isBefore(start)) {
            errors.rejectValue("endDate", "endDate.beforeStart",
                               "The event cannot end before it has started!");
        }

        // Creation time only validations
        if (event.getUpdated() == null) {
            if (start != null && start.isBefore(LocalDateTime.now())) {
                errors.rejectValue("startDate", "startDate.past",
                                   "The start date of a new event cannot be in the past!");
            }

            if (end != null && end.isBefore(LocalDateTime.now())) {
                errors.rejectValue("endDate", "endDate.past",
                                   "The end date of a new event cannot be in the past!");
            }
        }
    }
}
