package br.com.uniamerica.gajigo.entity.projection.room;

import br.com.uniamerica.gajigo.entity.Room;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Lecture;
import java.util.Set;

@Projection(name = "expandLectures", types = { Room.class })
public interface ExpandLectures {
    Set<Lecture> getLectures();
}
