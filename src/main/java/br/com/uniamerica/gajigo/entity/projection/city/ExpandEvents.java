package br.com.uniamerica.gajigo.entity.projection.city;

import br.com.uniamerica.gajigo.entity.City;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Event;
import java.util.Set;

@Projection(name = "expandEvents", types = { City.class })
public interface ExpandEvents {
    Set<Event> getEvents();
}
