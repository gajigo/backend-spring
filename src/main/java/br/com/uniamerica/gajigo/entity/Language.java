package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(schema = "public", name = "languages")
@SQLDelete(sql = "UPDATE languages SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class Language extends AbstractEntity {
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @OneToMany(mappedBy = "language")
    private Set<Lecture> lectures;
}
