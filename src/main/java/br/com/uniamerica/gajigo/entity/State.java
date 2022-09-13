package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "states",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "country_id"}))
@NoArgsConstructor
@Getter
@Setter
public class State extends AbstractLocation {
    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "state")
    private Set<City> cities = new HashSet<>();

    public State(String name) {
        super(name);
    }
}
