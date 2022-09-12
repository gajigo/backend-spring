package br.com.uniamerica.gajigo.entity.projection.state;

import br.com.uniamerica.gajigo.entity.State;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Country;

@Projection(name = "expandCountry", types = { State.class })
public interface ExpandCountry {
    Country getCountry();
}
