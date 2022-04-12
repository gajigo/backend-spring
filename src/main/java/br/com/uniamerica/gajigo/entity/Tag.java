package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "tags")
@SQLDelete(sql = "UPDATE tags SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class Tag extends AbstractDescribable {
    public Tag(String name, String description) {
        super(name, description);
    }

    @ManyToMany(mappedBy = "tags")
    private Set<Lecture> taggedLectures = new HashSet<>();
}
