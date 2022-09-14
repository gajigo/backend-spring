package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.User;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandParticipants", types = { Lecture.class })
public interface ExpandParticipants {
    Set<User> getParticipants();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
