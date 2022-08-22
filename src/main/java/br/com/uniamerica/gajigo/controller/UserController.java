package br.com.uniamerica.gajigo.controller;

import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin
@RepositoryRestController
public class UserController {
    UserRepository repository;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @PostMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody User user,
                                   PersistentEntityResourceAssembler assembler) {
        if (user == null || user.getUsername() == null || user.getPassword() == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        User found = repository.findFirstByUsername(user.getUsername());
        if (found == null || !found.getPassword().equals(user.getPassword())) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        return new ResponseEntity<>(assembler.toFullResource(found), HttpStatus.OK);
    }
}
