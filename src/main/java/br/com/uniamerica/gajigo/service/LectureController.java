package br.com.uniamerica.gajigo.service;

import br.com.uniamerica.gajigo.assembler.LectureModelAssembler;
import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.exception.LectureNotFoundException;
import br.com.uniamerica.gajigo.repository.LectureRepository;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping({"/lectures"})
public class LectureController {
    private final LectureRepository repository;
    private final LectureModelAssembler assembler;

    public LectureController(LectureRepository repository, LectureModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping(path = {"/{id}"})
    public EntityModel one(@PathVariable Long id) {
        Lecture lecture = repository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));

        return assembler.toModel(lecture);
    }

    /*
    @GetMapping(path = {"/{id}/participants"})
    public EntityModel participants(@PathVariable Long id) {
        Lecture lecture = repository.findById(id)
                .orElseThrow(() -> new LectureNotFoundException(id));

        return lecture.getParticipants();
    }
     */

    @GetMapping
    public CollectionModel all() {
        List lectures = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());

        return CollectionModel.of(lectures,
                linkTo(methodOn(LectureController.class).all()).withSelfRel());
    }

    @PostMapping
    public ResponseEntity save(@RequestBody Lecture lecture) {
        Lecture saved = new Lecture();

        saved.setName(lecture.getName());
        saved.setDescription(lecture.getDescription());
        saved.setParticipants(lecture.getParticipants());
        saved.setSpeakers(lecture.getSpeakers());

        EntityModel entityModel = assembler.toModel(repository.save(saved));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity replace(@PathVariable Long id,
                                  @RequestBody Lecture newLecture) {

        Lecture updated = repository.findById(id)
                .map(lecture -> {
                    lecture.setName(newLecture.getName());
                    lecture.setDescription(newLecture.getDescription());
                    lecture.setParticipants(newLecture.getParticipants());
                    lecture.setSpeakers(newLecture.getSpeakers());

                    return repository.save(lecture);
                })
                .orElseGet(() -> {
                    newLecture.setId(id);
                    return repository.save(newLecture);
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
