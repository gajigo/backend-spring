package br.com.uniamerica.gajigo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @JsonIgnore
    @Getter @Setter
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @JsonIgnore
    @Getter @Setter
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    @JsonIgnore
    @Getter @Setter
    private LocalDateTime updated;

    @Column(name = "removed", insertable = false)
    @JsonIgnore
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

    @PreRemove
    public void remove() {
        this.removed = LocalDateTime.now();
    }
}
