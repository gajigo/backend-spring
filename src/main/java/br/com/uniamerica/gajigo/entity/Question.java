package br.com.uniamerica.gajigo.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Set;

@Entity(name = "questions")
@Table(schema = "public")
public class Question {
    private String answer;
    private User responder;
    private Set<User> voters;
}
