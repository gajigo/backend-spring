package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@MappedSuperclass
@Getter @Setter
public abstract class AbstractLocation extends AbstractEntity {
    @Column(name = "name", nullable = false, unique = true)
    private String name;
}
