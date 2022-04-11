package br.com.uniamerica.gajigo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(schema = "public", name = "questions")
@NoArgsConstructor
@Getter @Setter
public class Question {
    private String answer;
    private User responder;
    private Set<User> voters;
}
