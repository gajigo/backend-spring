package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Language;
import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

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
    }

    private void validateName(Lecture lecture, Errors errors) {
        String name = lecture.getName();
        validateString("name", name, errors);
    }

    private void validateEvent(Lecture lecture, Errors errors) {
        Event event = lecture.getEvent();
        if (validateNull("event", event,
                         "Lecture must belong to an event!", errors)) {
            // Cannot do any more validations if the field is null
            return;
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
}
