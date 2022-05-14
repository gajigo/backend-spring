package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "documents",
       uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "type"}))
@SQLDelete(sql = "UPDATE documents SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class Document extends AbstractEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "value", nullable = false)
    private String value;
}
