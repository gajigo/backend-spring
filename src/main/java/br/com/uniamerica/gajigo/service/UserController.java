package br.com.uniamerica.gajigo.service;

import br.com.uniamerica.gajigo.assembler.UserModelAssembler;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.exception.UserNotFoundException;
import br.com.uniamerica.gajigo.repository.UserRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping({"/users"})
public class UserController {
    private final UserRepository repository;
    private final UserModelAssembler assembler;

    UserController(UserRepository repository, UserModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(path = {"/{id}"})
    public EntityModel one(@PathVariable Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @GetMapping(path = {"/byusername/{username}"})
    public EntityModel find(@PathVariable String username) {
        User user = repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return assembler.toModel(user);
    }

    @GetMapping
    public CollectionModel all() {
        List users = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody User user) {
        User saved = new User();

        saved.setUsername(user.getUsername());
        saved.setPassword(user.getPassword());
        saved.setName(user.getName());
        saved.setDescription(user.getDescription());

        EntityModel entityModel = assembler.toModel(repository.save(saved));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity replace(@PathVariable Long id,
                                  @RequestBody User newUser) {

        User updated = repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(newUser.getPassword());
                    user.setDescription(newUser.getDescription());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
        EntityModel entityModel = assembler.toModel(updated);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @DeleteMapping(path={"/{id}"})
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
