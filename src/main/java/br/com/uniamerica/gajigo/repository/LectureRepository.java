package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "lectures", path = "lectures")
@CrossOrigin
public interface LectureRepository extends GenericRepository<Lecture, Long> {
}
