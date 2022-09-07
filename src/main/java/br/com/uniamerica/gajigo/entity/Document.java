package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "documents",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "type"}))
@NoArgsConstructor
@Getter
@Setter
public class Document extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private String value;

    public Document(String type, String value) {
        this.type = type;
        this.value = value;
    }
}
