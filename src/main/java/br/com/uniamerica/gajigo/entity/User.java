package br.com.uniamerica.gajigo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "users")
@Where(clause = "removed IS null")
@NoArgsConstructor
@Getter @Setter
public class User extends AbstractDescribable {
    @Column(name = "admin", nullable = false)
    private boolean admin; // boolean is false by default

    @ManyToOne
    @JoinColumn(name = "location_id")
    private City location;

    @OneToMany(mappedBy = "user")
    private Set<Document> documents;

    @OneToMany(mappedBy = "owner")
    private Set<Event> events;

    @ManyToMany(mappedBy = "organizers")
    private Set<Event> organizes = new HashSet<>();

    @Column(name = "username", nullable = false, unique = true, length = 32)
    private String username;

    @Email
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false, length = 64)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "telephone")
    private String telephone;

    @ManyToMany(mappedBy = "participants")
    private Set<Lecture> participatesIn = new HashSet<>();

    @ManyToMany(mappedBy = "speakers")
    private Set<Lecture> speaksIn = new HashSet<>();

    public User(String username, String password, String name) {
        super(name, "");
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String name,
                String description) {
        this(username, password, name);
        this.setDescription(description);
    }

    public User(String username, String password, String name,
                String description, String email) {
        this(username, password, name, description);
        this.setEmail(email);
    }

    public User(String username, String password, String name,
                String description, String email, boolean isAdmin) {
        this(username, password, name, description, email);
        this.setAdmin(isAdmin);
    }


}
