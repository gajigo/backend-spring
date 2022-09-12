package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Event;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandEvent", types = { Lecture.class })
public interface ExpandEvent {
    Event getEvent();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
