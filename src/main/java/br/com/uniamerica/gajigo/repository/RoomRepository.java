package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "rooms", path = "rooms")
public interface RoomRepository extends JpaRepository<Room, Long> {
}
