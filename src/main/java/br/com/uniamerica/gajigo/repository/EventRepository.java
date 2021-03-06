package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "events", path = "events")
@CrossOrigin
public interface EventRepository extends JpaRepository<Event, Long> {
}
