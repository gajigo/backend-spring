package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.*;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LectureValidator extends AbstractValidator<Lecture> {
    public LectureValidator() {
        super(Lecture.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Lecture lecture = (Lecture) obj;

        validateName(lecture, errors);
        validateEvent(lecture, errors);
        validateLanguage(lecture, errors);
        validateAttendanceMode(lecture, errors);
        validateDate(lecture, errors);
        validateSpeakers(lecture, errors);
        validateRoom(lecture, errors);
    }

    private void validateName(Lecture lecture, Errors errors) {
        String name = lecture.getName();
        validateString("name", name, errors);
    }

    private void validateEvent(Lecture lecture, Errors errors) {
        Event event = lecture.getEvent();
        if (!validateNull("event", event,
                         "Lecture must belong to an event!", errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        if (event.getStatus() == EventStatus.EventCancelled) {
            errors.rejectValue("event", "event.cancelled",
                               "Lecture cannot be part of a cancelled event!");
        }

        // Creation time only validations
        if (lecture.getUpdated() == null) {
            // TODO remove null check for endDate here as well, events should always have those
            LocalDateTime start = event.getStartDate();
            LocalDateTime end = event.getEndDate();

            if (end != null && end.isBefore(LocalDateTime.now())) {
                errors.rejectValue("event", "lecture.eventEnded",
                                   "Cannot add a lecture to an event that has already ended!");
            }
        }
    }

    private void validateLanguage(Lecture lecture, Errors errors) {
        Language language = lecture.getLanguage();
        validateNull("language", language, errors);
    }

    private void validateAttendanceMode(Lecture lecture, Errors errors) {
        AttendanceMode mode = lecture.getAttendanceMode();
        if (!validateNull("attendanceMode", mode, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        List<String> modes = Arrays.stream(AttendanceMode.values())
                .map(m -> {
                    return m.name();
                })
                .collect(Collectors.toList());

        AttendanceMode eventMode = lecture.getEvent().getAttendanceMode();
        if (eventMode != AttendanceMode.Mixed && mode != eventMode) {
            errors.rejectValue("attendanceMode", "attendanceMode.conflictsWithEvent",
                               "Lecture Attendance Mode does not match mode of its Event! " +
                                       "Expected attendanceMode = " + eventMode.name());
        }
    }

    private void validateDate(Lecture lecture, Errors errors) {
        LocalDateTime start = lecture.getStartDate();
        LocalDateTime end = lecture.getEndDate();

        // TODO remove special logic for null, you shouldn't be able to schedule an lecture
        // TODO ... without, you know, scheduling it...
        if (end != null && end.isBefore(start)) {
            errors.rejectValue("endDate", "endDate.beforeStart",
                    "The lecture cannot end before it has started!");
        }

        // Creation time only validations
        if (lecture.getUpdated() == null) {
            if (start != null && start.isBefore(LocalDateTime.now())) {
                errors.rejectValue("startDate", "startDate.past",
                        "The start date of a new lecture cannot be in the past!");
            }

            if (end != null && end.isBefore(LocalDateTime.now())) {
                errors.rejectValue("endDate", "endDate.past",
                        "The end date of a new lecture cannot be in the past!");
            }
        }
    }

    private void validateSpeakers(Lecture lecture, Errors errors) {
        Set<User> speakers = lecture.getSpeakers();
        if (speakers == null || speakers.size() == 0) {
            errors.rejectValue("speakers", "speakers.empty",
                               "Lecture must have at least one speaker!");
        }
    }

    private void validateRoom(Lecture lecture, Errors errors) {
        Room room = lecture.getRoom();

        if (lecture.getAttendanceMode() == AttendanceMode.Online) {
            lecture.setRoom(null);
        } else if (room == null) {
            // Complain if mode isn't online and there isn't a room set
            errors.rejectValue("room", "room.null",
                               "Lecture with Mixed or Offline Attendance Mode should have a room!");
        } else {
            // When room isn't null and attendance mode is Offline or Mixed
            Set<Lecture> roomLectures = room.getLectures();
            for (Lecture roomLecture : roomLectures) {
                if (roomLecture.getName() == lecture.getName()) {
                    continue;
                }

                if (intervalOverlaps(lecture.getStartDate(), lecture.getEndDate(),
                        roomLecture.getStartDate(), roomLecture.getEndDate())) {
                    errors.rejectValue("room", "room.conflict",
                            "Lecture cannot take place in room with the specified timeframe " +
                                    "because another lecture is already scheduled during that period! " +
                                    "Lecture causing conflict takes place between " + roomLecture.getStartDate() +
                                    " and " + roomLecture.getEndDate());
                    break; // Optimization, assumes the lectures have already been verified to not be conflicting beforehand
                }
            }
        }
    }
}
