package br.com.uniamerica.gajigo.entity.projection.event;

import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Lecture;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandLectures", types = { Event.class })
public interface ExpandLectures {
    Set<Lecture> getLectures();
    EventStatus getStatus();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
