package br.com.uniamerica.gajigo.entity.projection.event;

import br.com.uniamerica.gajigo.entity.Event;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Room;
import java.util.Set;
import br.com.uniamerica.gajigo.entity.EventStatus;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandRooms", types = { Event.class })
public interface ExpandRooms {
    Set<Room> getRooms();
    String getLocation();
    EventStatus getStatus();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
