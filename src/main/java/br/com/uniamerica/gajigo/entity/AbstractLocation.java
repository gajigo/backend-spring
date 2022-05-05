package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
public abstract class AbstractLocation extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    public AbstractLocation(String name) {
        this.name = name;
    }
}
