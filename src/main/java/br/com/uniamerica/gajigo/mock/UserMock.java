package br.com.uniamerica.gajigo.mock;

import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.EventRepository;
import br.com.uniamerica.gajigo.repository.LectureRepository;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class UserMock {
    private Random rd;
    private Faker faker;

    private LectureRepository lectureRepository;
    private EventRepository eventRepository;

    @Autowired
    public UserMock(LectureRepository lectureRepository,
                    EventRepository eventRepository) {
        this.rd = new Random();
        this.faker = new Faker();
        this.lectureRepository = lectureRepository;
        this.eventRepository = eventRepository;
    }

    private User create() {
        User user = new User();

        user.setName(generateName());
        user.setDescription(generateDescription());

        user.setUsername(generateUsername());
        user.setPassword(generatePassword());

        user.setCpf(generateCpf());

        return user;
    }

    public List<User> create(int n) {
        ArrayList<User> users = new ArrayList();
        for (int i = 0; i < n; i++) {
            users.add(create());
        }

        return users;
    }

    private String generateName() {
        return faker.name().name();
    }

    private String generateDescription() {
        return faker.lorem().sentence();
    }

    private String generateUsername() {
        return faker.name().username();
    }

    private String generatePassword() {
        return faker.lorem().characters(10);
    }

    private String generateCpf() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                builder.append(randomNum());
            }
            builder.append(".");
        }
        builder.setLength(builder.length()-1);
        builder.append("-");
        for (int i = 0; i < 2; i++) {
            builder.append(randomNum());
        }

        return builder.toString();
    }

    private int randomNum() {
        return rd.nextInt(9) + 1;
    }
}
