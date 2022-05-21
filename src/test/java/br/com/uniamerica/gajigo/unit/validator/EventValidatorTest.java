package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.validator.EventValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.time.LocalDateTime;

public class EventValidatorTest extends AbstractValidatorTest<Event> {
    public EventValidatorTest() {
        super(new EventValidator(), Event.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Event event = new Event();
        Errors errors = validator.validate(event);

        assert errors.hasErrors();
    }

    @Test
    public void testNullLocation() throws Exception {
        Event event = validObject();
        event.setLocation(null);

        Errors errors = validator.validate(event);

        assert errors.getErrorCount() == 1;
    }

    @Test
    public void testEmptyName() throws Exception {
        Event event = validObject();
        event.setName("");

        Errors errors = validator.validate(event);

        assert errors.getErrorCount() == 1;
    }

    @Test
    public void testOnlineAttendanceSetsLocationToNull() throws Exception {
        Event event = validObject();
        event.setAttendanceMode(AttendanceMode.Online);

        Errors errors = validator.validate(event);

        assert !errors.hasErrors();
        assert event.getLocation() == null;
    }

    public Event validObject() {
        Event event = new Event("Test", "testevent",
                EventStatus.EventScheduled, AttendanceMode.Mixed);
        City city = new CityValidatorTest().validObject();
        User user = new UserValidatorTest().validObject();

        event.setLocation(city);
        event.setOwner(user);
        event.setStartDate(LocalDateTime.now().plusDays(1));
        event.setEndDate(LocalDateTime.now().plusYears(1));

        return event;
    }
}
