package br.com.uniamerica.gajigo.entity.projection.event;

import br.com.uniamerica.gajigo.entity.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "expandLectures", types = { Event.class })
public interface ExpandLectures {
    Set<Lecture> getLectures();
    EventStatus getStatus();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
