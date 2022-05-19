package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.*;
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
        validateOwner(event, errors);
        validateStatus(event, errors);
    }

    private void validateName(Event event, Errors errors) {
        String name = event.getName();
        validateString("name", name, errors);
    }

    private void validateAttendanceMode(Event event, Errors errors) {
        AttendanceMode mode = event.getAttendanceMode();
        if (!validateNull("attendanceMode", mode, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        // If the event is online, we can't have a location
        // If the event is offline or mixed, we must have one.
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

        if (!validateNull("startDate", start, errors) | // One | because we dont want short circuiting
            !validateNull("endDate", end, errors)) {
            return;
        }

        if (end.isBefore(start)) {
            errors.rejectValue("endDate", "endDate.beforeStart",
                               "The event cannot end before it has started!");
        }

        // Creation time only validations
        if (event.getUpdated() == null) {
            if (start.isBefore(LocalDateTime.now())) {
                errors.rejectValue("startDate", "startDate.past",
                                   "The start date of a new event cannot be in the past!");
            }

            if (end.isBefore(LocalDateTime.now())) {
                errors.rejectValue("endDate", "endDate.past",
                                   "The end date of a new event cannot be in the past!");
            }
        }
    }

    private void validateOwner(Event event, Errors errors) {
        User owner = event.getOwner();

        if (!validateNull("owner", owner,
                         "Event must have an owner!", errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        // Add the owner to the list of event organizers
        event.getOrganizers().add(owner);
    }

    private void validateStatus(Event event, Errors errors) {
        EventStatus status = event.getStatus();

        if (status == null) {
            event.setStatus(EventStatus.EventScheduled);
        }

        if (status == EventStatus.EventMovedOnline) {
            event.setAttendanceMode(AttendanceMode.Online);
            for (Lecture lecture : event.getLectures()) {
                lecture.setAttendanceMode(AttendanceMode.Online);
            }

            event.setLocation(null);
        }

        // Creation time only validations
        if (event.getUpdated() == null) {
            if (status != EventStatus.EventScheduled) {
                // Maybe a gotcha? Events are ALWAYS scheduled
                // at creation time, so I don't think it's worth
                // it to give an error message on wrong status
                event.setStatus(EventStatus.EventScheduled);
            }
        }
    }
}
