package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.User;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandSpeakers", types = { Lecture.class })
public interface ExpandSpeakers {
    Set<User> getSpeakers();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
