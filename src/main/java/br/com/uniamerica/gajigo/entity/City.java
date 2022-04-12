package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "cities")
@SQLDelete(sql = "UPDATE cities SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class City extends AbstractLocation {
    @ManyToOne(optional = false)
    @JoinColumn(name = "state_id")
    private State state;

    @OneToMany(mappedBy = "location")
    private Set<User> users;

    @OneToMany(mappedBy = "location")
    private Set<Event> events;
}
