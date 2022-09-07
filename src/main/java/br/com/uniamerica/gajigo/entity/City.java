package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "cities",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "state_id"}))
@NoArgsConstructor
@Getter
@Setter
public class City extends AbstractLocation {
    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "location")
    private Set<User> users;

    @OneToMany(mappedBy = "location")
    private Set<Event> events;

    public City(String name) {
        super(name);
    }
}
