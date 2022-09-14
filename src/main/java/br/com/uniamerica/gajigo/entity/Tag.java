package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "tags",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
@NoArgsConstructor
@Getter
@Setter
public class Tag extends AbstractDescribable {
    public Tag(String name, String description) {
        super(name, description);
    }

    @ManyToMany(mappedBy = "tags")
    private Set<Lecture> taggedLectures = new HashSet<>();
}
