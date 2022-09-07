package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "lectures",
        uniqueConstraints = @UniqueConstraint(columnNames = {"name", "event_id"}))
@NoArgsConstructor
@Getter
@Setter
public class Lecture extends AbstractDescribable {
    @ManyToOne(optional = false)
    @JoinColumn(name = "event_id")
    private Event event;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToMany
    @JoinTable(
            name = "lectures_tags",
            joinColumns = @JoinColumn(name = "lecture_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<Tag> tags = new HashSet<>();

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

    @Embedded
    private Interval interval;

    @Column(name = "attendance_mode", nullable = false)
    @Enumerated(EnumType.STRING)
    private AttendanceMode attendanceMode;

    public Lecture(String name, String description, Event event) {
        super(name, description);
        this.event = event;
    }
}
