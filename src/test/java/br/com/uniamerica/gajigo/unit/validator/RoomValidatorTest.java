package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Room;
import br.com.uniamerica.gajigo.validator.RoomValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class RoomValidatorTest extends AbstractValidatorTest<Room> {
    public RoomValidatorTest() {
        super(new RoomValidator(), Room.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Room room = new Room();
        Errors errors = validate(room);

        assert errors.hasErrors();
    }
}
