package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "languages", path = "languages")
@CrossOrigin
public interface LanguageRepository extends JpaRepository<Language, Long> {
    Language findFirstByName(String name);
}
