package br.com.uniamerica.gajigo.entity;

import br.com.uniamerica.gajigo.utils.JsonDeserializers;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(schema = "public", name = "users")
@NoArgsConstructor
@Getter
@Setter
public class User extends AbstractDescribable implements UserDetails {
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

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = JsonDeserializers.PasswordDeserializer.class)
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

    public static User build(final User user) {
        // TODO: Implementation needed
        final Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));


        User currentUser = new User();
        currentUser.setName(user.getName());
        currentUser.setActive(user.isActive());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());

        return currentUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();
        grantedAuthoritySet.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return grantedAuthoritySet;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return this.isActive();
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return this.isActive();
    }

    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return this.isActive();
    }

    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return this.isActive();
    }
}
