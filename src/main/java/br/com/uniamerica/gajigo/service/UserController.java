package br.com.uniamerica.gajigo.service;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @GetMapping("/user")
    public User getUser(@RequestParam(value = "id", defaultValue = "1") Long id) {
        Query query = new Session
    }
}
