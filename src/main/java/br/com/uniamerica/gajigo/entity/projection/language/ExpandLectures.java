package br.com.uniamerica.gajigo.entity.projection.language;

import br.com.uniamerica.gajigo.entity.Language;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Lecture;
import java.util.Set;
import java.lang.String;

@Projection(name = "expandLectures", types = { Language.class })
public interface ExpandLectures {
    Set<Lecture> getLectures();
    String getName();
}
