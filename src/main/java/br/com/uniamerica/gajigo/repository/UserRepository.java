package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "users", path = "users")
@CrossOrigin
public interface UserRepository extends JpaRepository<User, Long> {
    User findFirstByUsername(String username);
}
