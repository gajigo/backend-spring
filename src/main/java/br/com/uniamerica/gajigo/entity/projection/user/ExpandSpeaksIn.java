package br.com.uniamerica.gajigo.entity.projection.user;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Lecture;
import java.util.Set;

@Projection(name = "expandSpeaksIn", types = { User.class })
public interface ExpandSpeaksIn {
    Set<Lecture> getSpeaksIn();
    boolean getAdmin();
    String getUsername();
    String getEmail();
    String getPassword();
    String getTelephone();
}
