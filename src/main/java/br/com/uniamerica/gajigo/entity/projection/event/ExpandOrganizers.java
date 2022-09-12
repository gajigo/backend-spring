package br.com.uniamerica.gajigo.entity.projection.event;

import br.com.uniamerica.gajigo.entity.*;
import org.springframework.data.rest.core.config.Projection;

import java.util.Set;

@Projection(name = "expandOrganizers", types = { Event.class })
public interface ExpandOrganizers {
    Set<User> getOrganizers();
    EventStatus getStatus();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
