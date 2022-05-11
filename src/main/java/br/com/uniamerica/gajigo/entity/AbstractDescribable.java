package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractDescribable extends AbstractEntity {
    @NotNull(message = "Please enter a name!")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Please enter a description!")
    @Column(name = "description", nullable = false, length = 1024)
    private String description = "";

    public AbstractDescribable() {
    }

    public AbstractDescribable(String name) {
        this.name = name;
    }

    public AbstractDescribable(String name, String description) {
        this(name);
        this.description = description;
    }
}
