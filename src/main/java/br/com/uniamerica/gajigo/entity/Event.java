package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "events")
@SQLDelete(sql = "UPDATE events SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class Event extends AbstractDescribable {
    @ManyToOne
    @JoinColumn(name = "location_id")
    private City location;

    @ManyToOne(optional = false)
    @JoinColumn(name = "owner_id")
    private User owner;

    @ManyToMany
    @JoinTable(
            name = "events_organizers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "organizer_id")
    )
    private Set<User> organizers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Room> rooms = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Lecture> lectures = new HashSet<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "attendance_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    private AttendanceMode attendanceMode;

    public Event(String name, String description, EventStatus status) {
        super(name, description);
        this.status = status;
    }

    public Event(String name, String description, EventStatus status,
                 AttendanceMode attendanceMode) {
        this(name, description, status);
        this.attendanceMode = attendanceMode;
    }
}
