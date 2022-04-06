package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Table(schema = "public")
public class User extends AbstractDescribable {
    @Column(name = "login", nullable = false, unique = true, length = 32)
    @Getter @Setter
    private String login;

    @Column(name = "password", nullable = false, length = 64)
    @Getter @Setter
    private String password;

    @Column(name = "email", nullable = false, unique = true)
    @Getter @Setter
    private String email;

    @Column(name = "cpf", unique = true, length = 12)
    @Getter @Setter
    private String cpf;

    @OneToMany()
    @Getter @Setter
    private Set<Telephone> telephone = new HashSet();

    @ManyToMany(mappedBy = "participants")
    @Getter @Setter
    private Set<Lecture> participatesIn = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    @Getter @Setter
    private Set<Lecture> speaksIn = new HashSet<>();

    @Column(name = "birth_date")
    @Getter @Setter
    private LocalDate birthDate;
}
