package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "telephones")
@NoArgsConstructor
@Getter @Setter
public class Telephone extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(name = "number", nullable = false, length = 32)
    private String number;
}
