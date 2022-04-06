package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "users")
@Table(schema = "public")
public class User extends AbstractDescribable {
    @Column(name = "login", nullable = false, unique = true, length = 32)
    @Getter @Setter
    private String login;

    @Column(name = "password", nullable = false, length = 64)
    @Getter @Setter
    private String password;
}
