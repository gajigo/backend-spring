package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "languages",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NoArgsConstructor
@Getter
@Setter
public class Language extends AbstractEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Lecture> lectures;

    public Language(String name) {
        this.name = name;
    }
}
