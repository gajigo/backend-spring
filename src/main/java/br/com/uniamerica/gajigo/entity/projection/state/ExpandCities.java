package br.com.uniamerica.gajigo.entity.projection.state;

import br.com.uniamerica.gajigo.entity.State;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.City;
import java.util.Set;

@Projection(name = "expandCities", types = { State.class })
public interface ExpandCities {
    Set<City> getCities();
}
