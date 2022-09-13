package br.com.uniamerica.gajigo.entity.projection.user;

import br.com.uniamerica.gajigo.entity.User;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Document;
import java.util.Set;

@Projection(name = "expandDocuments", types = { User.class })
public interface ExpandDocuments {
    Set<Document> getDocuments();
    boolean getAdmin();
    String getProfileImage();
    String getUsername();
    String getEmail();
    String getPassword();
    String getTelephone();
}
