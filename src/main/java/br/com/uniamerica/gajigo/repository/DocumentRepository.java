package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "documents", path = "documents")
@CrossOrigin
public interface DocumentRepository extends JpaRepository<Document, Long> {
}
