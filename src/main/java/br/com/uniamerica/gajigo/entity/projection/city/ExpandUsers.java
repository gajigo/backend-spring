package br.com.uniamerica.gajigo.entity.projection.city;

import br.com.uniamerica.gajigo.entity.City;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.User;
import java.util.Set;

@Projection(name = "expandUsers", types = { City.class })
public interface ExpandUsers {
    Set<User> getUsers();
}
