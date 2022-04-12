package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "languages", path = "languages")
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
