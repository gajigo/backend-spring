package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    @Getter @Setter
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    @Getter @Setter
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    @LastModifiedDate
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
