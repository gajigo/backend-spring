package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.validator.EventValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class EventValidatorTest extends AbstractValidatorTest<Event> {
    public EventValidatorTest() {
        super(new EventValidator(), Event.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Event event = new Event();
        Errors errors = getValidator().validate(event);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testNullLocation() throws Exception {
        Event event = validObject();
        event.setLocation(null);

        Errors errors = getValidator().validate(event);

        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void testEmptyName() throws Exception {
        Event event = validObject();
        event.setName("");

        Errors errors = getValidator().validate(event);

        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void testOnlineAttendanceSetsLocationToNull() throws Exception {
        Event event = validObject();
        event.setAttendanceMode(AttendanceMode.Online);

        Errors errors = getValidator().validate(event);

        assertFalse(errors.hasErrors());
        assertNull(event.getLocation());
    }

    public Event validObject() {
        Event event = new Event("Test", "testevent",
                EventStatus.EventScheduled, AttendanceMode.Mixed);
        User user = new UserValidatorTest().validObject();
        Interval interval = new Interval(LocalDateTime.now().plusDays(1),
                LocalDateTime.now().plusYears(1));

        event.setLocation("são paulo");
        event.setOwner(user);
        event.setInterval(interval);

        return event;
    }
}
