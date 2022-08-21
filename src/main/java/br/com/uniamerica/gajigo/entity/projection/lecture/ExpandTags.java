package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Tag;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandTags", types = { Lecture.class })
public interface ExpandTags {
    Set<Tag> getTags();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
