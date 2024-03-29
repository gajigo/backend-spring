package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.State;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "states", path = "states")
@CrossOrigin
public interface StateRepository extends SoftDeleteRepository<State, Long> {
}
