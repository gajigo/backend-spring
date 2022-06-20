package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "cities", path = "cities")
@CrossOrigin
public interface CityRepository extends JpaRepository<City, Long> {
}
