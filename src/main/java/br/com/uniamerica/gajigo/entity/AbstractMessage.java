package br.com.uniamerica.gajigo.entity;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractMessage {
    @ManyToOne
    @JoinColumn(name = "sender_id")
    private User sender;
    @Column(name = "")
    private String text;
}
