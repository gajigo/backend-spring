package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Getter
@Setter
public abstract class AbstractMessage {
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;

    @Column(name = "text")
    private String text;
}
