package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(schema = "public", name = "telephones")
public class Telephone extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    @Getter @Setter
    private User user;

    @Column(name = "number", nullable = false, length = 32)
    @Getter @Setter
    private String number;
}
