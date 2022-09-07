package br.com.uniamerica.gajigo.repository;

import br.com.uniamerica.gajigo.entity.Room;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource(collectionResourceRel = "rooms", path = "rooms")
@CrossOrigin
public interface RoomRepository extends GenericRepository<Room, Long> {
}
