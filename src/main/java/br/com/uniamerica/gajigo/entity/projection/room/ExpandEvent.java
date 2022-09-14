package br.com.uniamerica.gajigo.entity.projection.room;

import br.com.uniamerica.gajigo.entity.Room;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Event;

@Projection(name = "expandEvent", types = { Room.class })
public interface ExpandEvent {
    Event getEvent();
}
