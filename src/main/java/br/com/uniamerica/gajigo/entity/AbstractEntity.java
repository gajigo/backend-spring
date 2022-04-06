package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter @Setter
    private Long id;

    @Column(name = "created", nullable = false)
    @Getter @Setter
    private LocalDateTime created;

    @Column(name = "updated")
    @Getter @Setter
    private LocalDateTime updated;

    @Column(name = "removed")
    @Getter @Setter
    private LocalDateTime removed;

    @PrePersist
    public void create() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.updated = LocalDateTime.now();
    }
}
