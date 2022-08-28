package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.ImageEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "imageEvent", path = "imageEvent")
@CrossOrigin
public interface ImageEventRepository extends JpaRepository<ImageEvent, Long> {
}
