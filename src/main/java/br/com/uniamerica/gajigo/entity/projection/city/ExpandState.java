package br.com.uniamerica.gajigo.entity.projection.city;

import br.com.uniamerica.gajigo.entity.City;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.State;

@Projection(name = "expandState", types = { City.class })
public interface ExpandState {
    State getState();
}
