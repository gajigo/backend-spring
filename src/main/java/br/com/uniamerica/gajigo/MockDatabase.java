package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.LectureRepository;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.jboss.logging.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockDatabase {
    private static final Logger log = LoggerFactory.logger(MockDatabase.class);

    @Bean
    CommandLineRunner initDatabase(LectureRepository lectureRepository, UserRepository userRepository) {
        return args -> {
            try {
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
                Lecture lecture1 = new Lecture("Movimento Agile",
                        "Como usar Agile para facillitar desenvolvimento");
                Lecture lecture2 = new Lecture("Microservicos Simples",
                        "Maneiras simples e verificadas de criar grandes (pequenos) microservicos");

                lecture1.getParticipants().add(userRepository.getById(1L));
                lecture1.getParticipants().add(userRepository.getById(3L));
                lecture1.getSpeakers().add(userRepository.getById(2L));

                lecture2.getParticipants().add(userRepository.getById(2L));
                lecture2.getSpeakers().add(userRepository.getById(1L));
                lecture2.getSpeakers().add(userRepository.getById(3L));

                log.info("Preloading " + lectureRepository.save(lecture1));
                log.info("Preloading " + lectureRepository.save(lecture2));
            } catch (Exception ignored) {
            }
        };
    }
}
