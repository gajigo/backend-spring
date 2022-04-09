package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "lectures", path = "lectures")
public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
