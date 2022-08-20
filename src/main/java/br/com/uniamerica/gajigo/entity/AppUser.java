package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
public class AppUser implements UserDetails {
    @Getter @Setter
    private String name;

    @Getter @Setter
    private String username;

    @Getter @Setter
    private boolean Active;

    @Getter @Setter
    private String password;

    @Getter @Setter
    private Collection<? extends GrantedAuthority> authorities;

    public AppUser(
            String name,
            String username,
            boolean active,
            String password,
            Collection<? extends GrantedAuthority> authorities
    ) {
        this.name = name;
        this.username = username;
        Active = active;
        this.password = password;
        this.authorities = authorities;
    }

    public static AppUser build(final User user) {
        // TODO: Implementation needed
        final Set<GrantedAuthority> grantedAuthoritySet = new HashSet<>();

        AppUser currentUser = new AppUser();
        currentUser.setName(user.getName());
        currentUser.setActive(user.isActive());
        currentUser.setUsername(user.getUsername());
        currentUser.setPassword(user.getPassword());
        currentUser.setAuthorities(grantedAuthoritySet);

        return currentUser;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return this.isActive();
    }

    @Override
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
