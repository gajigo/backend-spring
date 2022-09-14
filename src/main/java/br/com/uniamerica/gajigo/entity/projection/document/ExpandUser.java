package br.com.uniamerica.gajigo.entity.projection.document;

import br.com.uniamerica.gajigo.entity.Document;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.User;

@Projection(name = "expandUser", types = { Document.class })
public interface ExpandUser {
    User getUser();
    String getType();
    String getValue();
}
