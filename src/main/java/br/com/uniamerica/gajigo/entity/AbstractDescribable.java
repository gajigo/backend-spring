package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractDescribable extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false, length = 1024)
    private String description = "";

    public AbstractDescribable() {
    }

    public AbstractDescribable(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
