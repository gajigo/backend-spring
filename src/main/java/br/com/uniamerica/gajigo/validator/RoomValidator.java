package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Room;
import org.springframework.validation.Errors;

public class RoomValidator extends AbstractValidator<Room> {
    public RoomValidator() {
        super(Room.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Room room = (Room) obj;

        validateEvent(room, errors);
        validateName(room, errors);
        validateDescription(room, errors);
    }

    private void validateEvent(Room room, Errors errors) {
        Event event = room.getEvent();
        validateNull("event", event,
                     "Room must belong to an event!", errors);
    }

    private void validateName(Room room, Errors errors) {
        String name = room.getName();
        validateString("name", name, errors);
    }

    private void validateDescription(Room room, Errors errors) {
        String description = room.getDescription();
        if (description == null) {
            description = "";
        }

        room.setDescription(description);
    }
}
