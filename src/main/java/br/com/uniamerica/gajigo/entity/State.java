package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "states")
@SQLDelete(sql = "UPDATE states SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class State extends AbstractLocation {
    @ManyToOne(optional = false)
    @JoinColumn(name = "country_id")
    private Country country;

    @OneToMany(mappedBy = "state")
    private Set<City> cities = new HashSet<>();
}
