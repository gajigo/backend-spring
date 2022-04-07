package br.com.uniamerica.gajigo.assembler;

import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.service.LectureController;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LectureModelAssembler implements RepresentationModelAssembler<Lecture, EntityModel<Lecture>> {
    @Override
    public EntityModel toModel(Lecture lecture) {
        return EntityModel.of(lecture,
                linkTo(methodOn(LectureController.class).one(lecture.getId())).withSelfRel(),
                linkTo(methodOn(LectureController.class).all()).withRel("lectures"));
    }
}
