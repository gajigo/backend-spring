package br.com.uniamerica.gajigo.unit.repository;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.repository.EventRepository;
import br.com.uniamerica.gajigo.repository.LectureRepository;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LectureRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Test
    public void testLectureInsert() {
        User user = new User("username", "password", "name");
        user.setEmail("email@email.com");
        userRepository.save(user);

        Event event = new Event("new event",
                "a new event",
                EventStatus.EventPostponed,
                AttendanceMode.Online);
        event.setOwner(user);
        eventRepository.save(event);

        Lecture lecture = new Lecture("new lecture",
                "a new lecture",
                event);
        lecture.setAttendanceMode(AttendanceMode.Online);
        lectureRepository.save(lecture);

        assertEquals(1, lectureRepository.count());
    }

    @Test
    public void testLectureDelete() {
        User user = new User("username", "password", "name");
        user.setEmail("email@email.com");
        userRepository.save(user);

        Event event = new Event("new event",
                "a new event",
                EventStatus.EventPostponed,
                AttendanceMode.Online);
        event.setOwner(user);
        eventRepository.save(event);

        Lecture lecture = new Lecture("new lecture",
                "a new lecture",
                event);
        lecture.setAttendanceMode(AttendanceMode.Online);
        lectureRepository.save(lecture);
        lectureRepository.delete(lecture);

        assertEquals(0, lectureRepository.count());
    }

    @Test
    public void testLectureUpdate() {
        User user = new User("username", "password", "name");
        user.setEmail("email@email.com");
        userRepository.save(user);

        Event event = new Event("new event",
                "a new event",
                EventStatus.EventPostponed,
                AttendanceMode.Online);
        event.setOwner(user);
        eventRepository.save(event);

        Lecture lecture = new Lecture("new lecture",
                "a new lecture",
                event);
        lecture.setAttendanceMode(AttendanceMode.Online);
        lectureRepository.save(lecture);
        lecture.setName("new name lecture");
        lectureRepository.save(lecture);

        assertEquals("new name lecture", lecture.getName());
    }
}
