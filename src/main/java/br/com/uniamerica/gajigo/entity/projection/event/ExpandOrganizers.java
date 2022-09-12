package br.com.uniamerica.gajigo.entity.projection.event;

import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.User;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandOrganizers", types = { Event.class })
public interface ExpandOrganizers {
    Set<User> getOrganizers();
    EventStatus getStatus();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
