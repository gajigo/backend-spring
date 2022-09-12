package br.com.uniamerica.gajigo.entity.projection.lecture;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.data.rest.core.config.Projection;
import br.com.uniamerica.gajigo.entity.Language;
import br.com.uniamerica.gajigo.entity.Interval;
import br.com.uniamerica.gajigo.entity.AttendanceMode;

@Projection(name = "expandLanguage", types = { Lecture.class })
public interface ExpandLanguage {
    Language getLanguage();
    Interval getInterval();
    AttendanceMode getAttendanceMode();
}
