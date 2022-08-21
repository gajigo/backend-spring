package br.com.uniamerica.gajigo.entity.projection.user;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Event;
import java.util.Set;
import java.lang.String;
import java.lang.String;
import java.lang.String;
import java.lang.String;

@Projection(name = "expandEvents", types = { User.class })
public interface ExpandEvents {
    Set<Event> getEvents();
    boolean getAdmin();
    String getUsername();
    String getEmail();
    String getPassword();
    String getTelephone();
}
