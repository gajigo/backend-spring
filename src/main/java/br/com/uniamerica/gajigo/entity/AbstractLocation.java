package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@MappedSuperclass
@NoArgsConstructor
@Getter @Setter
public abstract class AbstractLocation extends AbstractEntity {
    @Column(name = "name", nullable = false)
    private String name;

    public AbstractLocation(String name) {
        this.name = name;
    }
}
