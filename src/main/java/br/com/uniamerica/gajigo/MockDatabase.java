package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.mock.CountryMock;
import br.com.uniamerica.gajigo.mock.LanguageMock;
import br.com.uniamerica.gajigo.mock.UserMock;
import br.com.uniamerica.gajigo.repository.*;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDatabase {
    private static final Logger log = LoggerFactory.logger(MockDatabase.class);

    @Autowired
    private LanguageRepository languageRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private LectureRepository lectureRepository;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner initDatabase() {
        return args -> {
            try {
                /*
                // Events
                Event event1 = new Event(
                        "Uniamerica Eventos", "Palestras e Demais", EventStatus.EventScheduled,
                        AttendanceMode.Mixed
                );

                // Users
                log.info("Preloading " + userRepository.save(new User(
                        "pedro@projetodigital.com.br",
                        "pedro", "hunter2",
                        "Pedro Henrique Garcia", "Fullstack Developer"
                )));
                log.info("Preloading " + userRepository.save(new User(
                        "changzer@gmail.com",
                        "abel", "hunter3",
                        "Abel Chang", "Frontend Developer"
                )));
                log.info("Preloading " + userRepository.save(new User(
                        "jeanclayton@gmail.com",
                        "jean", "senhamaneira",
                        "Jean Clayton", "Backend Developer"
                )));

                event1.setOwner(userRepository.getById(1L));
                log.info("Preloading " + eventRepository.save(event1));

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

                lecture1.setAttendanceMode(AttendanceMode.Online);
                lecture2.setAttendanceMode(AttendanceMode.Offline);

                log.info("Preloading " + lectureRepository.save(lecture1));
                log.info("Preloading " + lectureRepository.save(lecture2));

                // Users
                UserMock userMock = new UserMock(cityRepository);
                for (User user : userMock.create(100)) {
                    log.info("Preloading " + userRepository.save(user));
                }
                 */

                /*
                // Countries
                CountryMock countryMock = new CountryMock();
                for (Country country : countryMock.create(1000)) {
                    try {
                        log.info("Preloading " + countryRepository.save(country));
                    } catch (Exception ignored) {}
                }
                 */

                /*
                // Languages
                LanguageMock languageMock = new LanguageMock();
                for (Language language : languageMock.create(1000)) {
                    try {
                        log.info("Preloading " + languageRepository.save(language));
                    } catch (Exception ignored) {}
                }
                 */
            } catch (Exception ignored) {}
        };
    }
}
