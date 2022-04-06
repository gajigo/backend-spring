package br.com.uniamerica.gajigo.service;

import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LectureController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/lecture")
    public Lecture getUser(@RequestParam(value = "id", defaultValue = "1") Long id) {
        User user1 = new User();
        user1.setName("1");
        user1.setLogin("test");
        user1.setPassword("pass");

        User user2 = new User();
        user2.setName("2");
        user2.setLogin("test");
        user2.setPassword("pass");

        Lecture lecture = new Lecture();
        lecture.setName("testLecture");
        lecture.setDescription("funcionou?");
        lecture.getParticipants().add(user1);
        lecture.getSpeakers().add(user2);

        userRepository.save(user1);

        return lecture;
    }
}
