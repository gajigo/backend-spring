package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime created;

    @Column(name = "updated", insertable = false)
    @LastModifiedDate
    private LocalDateTime updated;

    @Column(name = "removed")
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
