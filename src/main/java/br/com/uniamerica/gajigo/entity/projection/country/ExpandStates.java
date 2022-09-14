package br.com.uniamerica.gajigo.entity.projection.country;

import br.com.uniamerica.gajigo.entity.Country;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.State;
import java.util.Set;

@Projection(name = "expandStates", types = { Country.class })
public interface ExpandStates {
    Set<State> getStates();
}
