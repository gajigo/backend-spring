package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.AttendanceMode;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.EventRepository;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;


public class EventRepositoryTest  extends AbstractRepositoryTest{
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testEventInsert() {
        User user = new User("eduardo123", "minhasenha123", "eduardo de souza magalhaes");
        user.setEmail("eduardo@gmail.com");
        user = userRepository.save(user);

        Event event = new Event(
                "novo evento",
                "descricao do evento",
                EventStatus.EventPostponed,
                AttendanceMode.Online);
        event.setOwner(user);
        eventRepository.save(event);

        assertEquals(1, eventRepository.findAll().size());
    }

    @Test
    public void testEventUpdate() {

    }

    @Test
    public void testEventDelete() {

    }
}
