package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;

    @Column(name = "created", nullable = false, updatable = false)
    @CreatedDate
    protected LocalDateTime created;

    @Column(name = "updated", insertable = false)
    @LastModifiedDate
    private LocalDateTime updated;

    @Column(name = "removed")
    private Boolean removed;

    @Column(name = "removeDate")
    protected LocalDateTime removeDate;

    @PrePersist
    public void create() {
        this.created = LocalDateTime.now();
    }

    @PreUpdate
    public void update() {
        this.updated = LocalDateTime.now();

        if (removed && removeDate == null) {
            this.setRemoveDate(LocalDateTime.now());
        } else {
            this.setRemoveDate(null);
        }
    }
}
