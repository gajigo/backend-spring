package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "countries",
       uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class Country extends AbstractLocation {
    @OneToMany(mappedBy = "country")
    private Set<State> states = new HashSet<>();

    public Country(String name) {
        super(name);
    }
}
