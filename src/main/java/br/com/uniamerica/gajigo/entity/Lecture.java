package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "lectures")
@Table(schema = "public")
@SQLDelete(sql = "UPDATE lectures SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
public class Lecture extends AbstractDescribable {
    @ManyToMany
    @JoinTable(
            name = "lectures_participants",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    @Getter @Setter
    private Set<User> participants = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "lectures_speakers",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    @Getter @Setter
    private Set<User> speakers = new HashSet<>();

    public Lecture() {
    }

    public Lecture(String name, String description) {
        super(name, description);
    }
}
