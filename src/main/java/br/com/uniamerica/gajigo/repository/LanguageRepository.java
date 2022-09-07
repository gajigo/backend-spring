package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Language;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "languages", path = "languages")
@CrossOrigin
public interface LanguageRepository extends GenericRepository<Language, Long> {
    Language findFirstByName(String name);
}
