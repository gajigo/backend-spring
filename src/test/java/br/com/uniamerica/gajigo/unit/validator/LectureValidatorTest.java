package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.validator.LectureValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LectureValidatorTest extends AbstractValidatorTest<Lecture> {
    public LectureValidatorTest() {
        super(new LectureValidator(), Lecture.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Lecture lecture = new Lecture();
        Errors errors = getValidator().validate(lecture);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        Lecture lecture = validObject();
        lecture.setName("");

        Errors errors = getValidator().validate(lecture);

        assertEquals(1, errors.getErrorCount());
    }

    public Lecture validObject() {
        Event event = new EventValidatorTest().validObject();
        Lecture lecture = new Lecture("Test", "test", event);
        Language language = new LanguageValidatorTest().validObject();
        User user = new UserValidatorTest().validObject();
        Room room = new RoomValidatorTest().validObject();
        Interval interval = new Interval(event.getInterval().getStartDate().plusDays(1),
                event.getInterval().getEndDate().minusDays(2));

        lecture.setSpeakers(new HashSet<>());
        lecture.getSpeakers().add(user);
        lecture.setLanguage(language);
        lecture.setAttendanceMode(AttendanceMode.Mixed);
        lecture.setRoom(room);
        room.setLectures(new HashSet<>());
        room.getLectures().add(lecture);
        lecture.setInterval(interval);

        return lecture;
    }
}
