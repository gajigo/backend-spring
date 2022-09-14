package br.com.uniamerica.gajigo.entity.projection.tag;

import br.com.uniamerica.gajigo.entity.Tag;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Lecture;
import java.util.Set;

@Projection(name = "expandTaggedLectures", types = { Tag.class })
public interface ExpandTaggedLectures {
    Set<Lecture> getTaggedLectures();
}
