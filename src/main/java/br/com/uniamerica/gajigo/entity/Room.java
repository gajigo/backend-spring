package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "rooms",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "event_id"}))
@NoArgsConstructor
@Getter
@Setter
public class Room extends AbstractDescribable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @OneToMany(mappedBy = "room")
    private Set<Lecture> lectures;

    public Room(String name) {
        super(name);
    }

    public Room(String name, String description) {
        super(name, description);
    }
}
