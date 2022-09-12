package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Room;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandRoom", types = { Lecture.class })
public interface ExpandRoom {
    Room getRoom();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
