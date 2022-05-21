package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.*;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;
import java.util.Set;

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
        validateInterval(lecture, errors);
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

        AttendanceMode eventMode = lecture.getEvent().getAttendanceMode();
        if (eventMode != AttendanceMode.Mixed && mode != eventMode) {
            errors.rejectValue("attendanceMode", "attendanceMode.conflictsWithEvent",
                               "Lecture Attendance Mode does not match mode of its Event! " +
                                       "Expected attendanceMode = " + eventMode.name());
        }
    }

    private void validateInterval(Lecture lecture, Errors errors) {
        Interval interval = lecture.getInterval();

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
                    "The lecture cannot end before it has started!");
            return;
        }

        Interval eventInterval = lecture.getEvent().getInterval();

        if (!eventInterval.hasInside(interval)) {
            errors.rejectValue("interval", "date.outsideEventDate",
                               "Lecture startDate and endDate must be completely contained inside its event!" +
                                       "\nExpected startDate after " + eventInterval.getStartDate() +
                                       " and endDate before " + eventInterval.getEndDate() + ".");
        }

        // Creation time only validations
        if (lecture.getUpdated() == null) {
            if (interval.getStartDate().isBefore(LocalDateTime.now())) {
                errors.rejectValue("interval", "startDate.past",
                        "The startDate of a new lecture cannot be in the past!");
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

                if (roomLecture.getInterval().isOverlapping(lecture.getInterval())) {
                    errors.rejectValue("room", "room.conflict",
                            "Lecture cannot take place in room with the specified timeframe " +
                                    "because another lecture is already scheduled during that period! " +
                                    "Lecture causing conflict takes place between " + roomLecture.getInterval()
                                    .getStartDate() + " and " + roomLecture.getInterval().getEndDate() + ".");
                    break; // Optimization, assumes the lectures have already been verified to not be conflicting beforehand
                }
            }
        }
    }
}
