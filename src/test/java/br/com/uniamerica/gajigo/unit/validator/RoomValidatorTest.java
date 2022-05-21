package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Room;
import br.com.uniamerica.gajigo.validator.RoomValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

public class RoomValidatorTest extends AbstractValidatorTest<Room> {
    public RoomValidatorTest() {
        super(new RoomValidator(), Room.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Room room = new Room();
        Errors errors = validator.validate(room);

        assert errors.hasErrors();
    }

    @Test
    public void testNullDescriptionSetsEmpty() throws Exception {
        Room room = validObject();
        room.setDescription(null);

        Errors errors = validator.validate(room);

        assert !errors.hasErrors();
        assert room.getDescription() == "";
    }

    @Test
    public void testEmptyName() throws Exception {
        Room room = validObject();
        room.setName("");

        Errors errors = validator.validate(room);

        assert errors.getErrorCount() == 1;
    }

    public Room validObject() {
        Room room = new Room("Test", "test");
        Event event = new EventValidatorTest().validObject();
        room.setEvent(event);

        return room;
    }
}
