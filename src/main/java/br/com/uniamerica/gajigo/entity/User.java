package br.com.uniamerica.gajigo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Table(schema = "public")
@SQLDelete(sql = "UPDATE users SET removed = CURRENT_TIMESTAMP WHERE id = ?")
@Where(clause = "removed IS null")
public class User extends AbstractDescribable {
    @Column(name = "username", nullable = false, unique = true, length = 32)
    @Getter @Setter
    private String username;

    @Column(name = "password", nullable = false, length = 64)
    @Getter @Setter
    private String password;

    @ManyToMany(mappedBy = "participants")
    @JsonIgnore
    @Getter @Setter
    private Set<Lecture> participatesIn = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    @JsonIgnore
    @Getter @Setter
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
