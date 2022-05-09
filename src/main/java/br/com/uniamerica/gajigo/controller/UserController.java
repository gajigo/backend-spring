package br.com.uniamerica.gajigo.controller;

import br.com.uniamerica.gajigo.entity.Bulk;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@BasePathAwareController
@RepositoryRestController
@RequestMapping(value = "users")
@ExposesResourceFor(User.class)
public class UserController {
    private final UserRepository repo;
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public UserController(UserRepository repo, RepositoryEntityLinks entityLinks) {
        this.repo = repo;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/batch")
    public @ResponseBody ResponseEntity<?> bulkCreate(@RequestBody Bulk<User> bulk) {
        Iterable<User> users = repo.saveAll(bulk.getContent());
        ArrayList<EntityModel<User>> models = new ArrayList<>();

        users.forEach(entity -> {
            Link link = entityLinks.linkToItemResource(User.class, entity.getId()).withRel("self");
            models.add(EntityModel.of(entity).add(link));
        });

        return new ResponseEntity<>(CollectionModel.of(models), HttpStatus.CREATED);
    }
}
