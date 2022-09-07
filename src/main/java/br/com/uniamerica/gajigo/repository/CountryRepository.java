package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Country;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "countries", path = "countries")
@CrossOrigin
public interface CountryRepository extends GenericRepository<Country, Long> {
    Country findFirstByName(String name);
}
