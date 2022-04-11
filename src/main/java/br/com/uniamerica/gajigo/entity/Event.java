package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
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
@Getter @Setter
public class Event extends AbstractDescribable {
    @ManyToMany
    @JoinTable(
            name = "events_organizers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "organizer_id")
    )
    private Set<User> organizers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    private Set<Lecture> lectures = new HashSet<>();

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public Event() {
    }

    public Event(String name, String description, EventStatus status) {
        super(name, description);
        this.status = status;
    }
}
