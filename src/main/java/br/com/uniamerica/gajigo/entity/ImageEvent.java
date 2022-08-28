package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ImageEvent extends AbstractDescribable {
    @Column(name = "url_image")
    private String url;

    @JoinColumn(name = "event_id")
    @ManyToOne
    private Event event;
}
