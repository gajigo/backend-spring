package br.com.uniamerica.gajigo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "users")
@SQLDelete(sql = "UPDATE users SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
@Getter @Setter
public class User extends AbstractDescribable {
    @ManyToMany(mappedBy = "organizers")
    private Set<Event> organizes = new HashSet<>();

    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "cpf")
    private String cpf;

    @ManyToMany(mappedBy = "participants")
    private Set<Lecture> participatesIn = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    private Set<Lecture> speaksIn = new HashSet<>();

    public User() {
    }

    public User(String username, String password, String name) {
        super(name, "");
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name, String description) {
        this(username, password, name);
        this.setDescription(description);
    }
}
