package br.com.uniamerica.gajigo.controller;

import br.com.uniamerica.gajigo.entity.Bulk;
import br.com.uniamerica.gajigo.entity.Tag;
import br.com.uniamerica.gajigo.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.BasePathAwareController;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;

@BasePathAwareController
@RepositoryRestController
@RequestMapping(value = "tags")
@ExposesResourceFor(Tag.class)
public class TagController {
    private final TagRepository repo;
    private final RepositoryEntityLinks entityLinks;

    @Autowired
    public TagController(TagRepository repo, RepositoryEntityLinks entityLinks) {
        this.repo = repo;
        this.entityLinks = entityLinks;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/batch")
    public @ResponseBody ResponseEntity<?> bulkCreate(@RequestBody Bulk<Tag> bulk) {
        Iterable<Tag> tags = repo.saveAll(bulk.getContent());
        ArrayList<EntityModel<Tag>> models = new ArrayList<>();

        tags.forEach(entity -> {
            Link link = entityLinks.linkToItemResource(Tag.class, entity.getId()).withRel("self");
            models.add(EntityModel.of(entity).add(link));
        });

        return new ResponseEntity<>(CollectionModel.of(models), HttpStatus.CREATED);
    }
}
