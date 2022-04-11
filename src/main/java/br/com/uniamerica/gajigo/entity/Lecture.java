package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "lectures")
@SQLDelete(sql = "UPDATE lectures SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@Getter @Setter
public class Lecture extends AbstractDescribable {
    @ManyToOne(optional = false)
    private Event event;

    @ManyToMany
    @JoinTable(
            name = "lectures_participants",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "participant_id")
    )
    private Set<User> participants = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "lectures_speakers",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private Set<User> speakers = new HashSet<>();

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    public Lecture() {
    }

    public Lecture(String name, String description, Event event) {
        super(name, description);
        this.event = event;
    }
}
