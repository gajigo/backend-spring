package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "tags", path = "tags")
@CrossOrigin
public interface TagRepository extends JpaRepository<Tag, Long> {
    Tag findFirstByName(String name);
}
