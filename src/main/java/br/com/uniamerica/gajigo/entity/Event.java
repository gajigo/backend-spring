package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "events")
@SQLDelete(sql = "UPDATE events SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
public class Event extends AbstractDescribable {
    @ManyToMany
    @JoinTable(
            name = "events_organizers",
            joinColumns = @JoinColumn(name = "event_id"),
            inverseJoinColumns = @JoinColumn(name = "organizer_id")
    )
    @Getter @Setter
    private Set<User> organizers = new HashSet<>();

    @OneToMany(mappedBy = "event")
    @Getter @Setter
    private Set<Lecture> lectures = new HashSet<>();

    @Column(name = "status", nullable = false)
    @Getter @Setter
    private EventStatus status;

    public Event() {
    }

    public Event(String name, String description, EventStatus status) {
        super(name, description);
        this.status = status;
    }
}
