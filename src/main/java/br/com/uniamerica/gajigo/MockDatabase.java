package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.mock.UserMock;
import br.com.uniamerica.gajigo.repository.EventRepository;
import br.com.uniamerica.gajigo.repository.LectureRepository;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
@ComponentScan
public class MockDatabase {
    private static final Logger log = LoggerFactory.logger(MockDatabase.class);

    private EventRepository eventRepository;
    private LectureRepository lectureRepository;
    private UserRepository userRepository;

    @Autowired
    public MockDatabase(EventRepository eventRepository,
                        LectureRepository lectureRepository,
                        UserRepository userRepository) {
        this.eventRepository = eventRepository;
        this.lectureRepository = lectureRepository;
        this.userRepository = userRepository;
    }

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            try {
                // Events
                Event event1 = new Event(
                        "Uniamerica Eventos", "Palestras e Demais", EventStatus.EventScheduled
                );

                log.info("Preloading " + eventRepository.save(event1));

                // Users
                log.info("Preloading " + userRepository.save(new User(
                        "pedro", "hunter2", "Pedro Henrique Garcia", "Fullstack Developer"
                )));
                log.info("Preloading " + userRepository.save(new User(
                        "abel", "hunter3", "Abel Chang", "Frontend Developer"
                )));
                log.info("Preloading " + userRepository.save(new User(
                        "jean", "senhamaneira", "Jean Clayton", "Backend Developer"
                )));

                // Lectures
                Lecture lecture1 = new Lecture(
                        "Movimento Agile",
                        "Como usar Agile para facillitar desenvolvimento",
                        event1);
                Lecture lecture2 = new Lecture(
                        "Microservicos Simples",
                        "Maneiras simples e verificadas de criar grandes (pequenos) microservicos",
                        event1);

                lecture1.getParticipants().add(userRepository.getById(1L));
                lecture1.getParticipants().add(userRepository.getById(3L));
                lecture1.getSpeakers().add(userRepository.getById(2L));

                lecture2.getParticipants().add(userRepository.getById(2L));
                lecture2.getSpeakers().add(userRepository.getById(1L));
                lecture2.getSpeakers().add(userRepository.getById(3L));

                UserMock mock = new UserMock(lectureRepository, eventRepository);
                for (User user : mock.create(100)) {
                    log.info("Preloading " + userRepository.save(user));
                }

                log.info("Preloading " + lectureRepository.save(lecture1));
                log.info("Preloading " + lectureRepository.save(lecture2));
            } catch (Exception ignored) {
            }
        };
    }
}
