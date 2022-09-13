package br.com.uniamerica.gajigo.entity.projection.user;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Event;
import java.util.Set;

@Projection(name = "expandOrganizes", types = { User.class })
public interface ExpandOrganizes {
    Set<Event> getOrganizes();
    boolean getAdmin();
    String getProfileImage();
    String getUsername();
    String getEmail();
    String getPassword();
    String getTelephone();
}
