package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Room;
import br.com.uniamerica.gajigo.validator.RoomValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.*;

public class RoomValidatorTest extends AbstractValidatorTest<Room> {
    public RoomValidatorTest() {
        super(new RoomValidator(), Room.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Room room = new Room();
        Errors errors = getValidator().validate(room);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testNullDescriptionSetsEmpty() throws Exception {
        Room room = validObject();
        room.setDescription(null);

        Errors errors = getValidator().validate(room);

        assertFalse(errors.hasErrors());
        assertEquals("", room.getDescription());
    }

    @Test
    public void testEmptyName() throws Exception {
        Room room = validObject();
        room.setName("");

        Errors errors = getValidator().validate(room);

        assertEquals(1, errors.getErrorCount());
    }

    public Room validObject() {
        Room room = new Room("Test", "test");
        Event event = new EventValidatorTest().validObject();
        room.setEvent(event);

        return room;
    }
}
