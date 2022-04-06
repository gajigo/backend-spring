package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "lectures")
@Table(schema = "public")
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
}
