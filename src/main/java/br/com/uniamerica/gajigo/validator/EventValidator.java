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
        validateInterval(event, errors);
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
        } else if (event.getLocation() == null) {
            errors.rejectValue("location", "location.null",
                    "Event location cannot be null if " +
                            "Attendance is Mixed or Offline!");
        }
    }

    private void validateInterval(Event event, Errors errors) {
        Interval interval = event.getInterval();

        if (interval == null) {
            errors.rejectValue("interval", "startDate.null",
                    "startDate must not be null!");
            errors.rejectValue("interval", "endDate.null",
                    "endDate must not be null!");
            return;
        }

        if (!validateNull("interval", interval.getStartDate(), errors) | // One | because we dont want short circuiting
                !validateNull("interval", interval.getEndDate(), errors)) {
            return;
        }

        if (!interval.valid()) {
            errors.rejectValue("interval", "endDate.beforeStart",
                    "The event cannot end before it has started!");
            return;
        }

        // Creation time only validations
        if (event.getUpdated() == null) {
            if (interval.getStartDate().isBefore(LocalDateTime.now())) {
                errors.rejectValue("interval", "startDate.past",
                        "The startDate of a new event cannot be in the past!");
            }
            // No need to check end because end > start
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
