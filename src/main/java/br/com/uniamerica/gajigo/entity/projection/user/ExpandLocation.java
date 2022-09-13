package br.com.uniamerica.gajigo.entity.projection.user;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.City;

@Projection(name = "expandLocation", types = { User.class })
public interface ExpandLocation {
    City getLocation();
    boolean getAdmin();
    String getProfileImage();
    String getUsername();
    String getEmail();
    String getPassword();
    String getTelephone();
}
