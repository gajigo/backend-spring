package br.com.uniamerica.gajigo.service;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public User getUser(@RequestParam(value = "id", defaultValue = "1") Long id) {
        User user = new User();
        user.setName("funcionaldo");
        user.setDescription("se esta vendo isso, o teste funcionou!");
        user.setLogin("test");
        user.setPassword("pass");

        return user;
    }
}
