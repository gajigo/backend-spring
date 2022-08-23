package br.com.uniamerica.gajigo.mock;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class AbstractMock<T> {
    @Getter
    private Random rd = new Random();
    @Getter
    private Faker faker = new Faker();

    abstract T create();

    public List<T> create(int n) {
        ArrayList<T> entities = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            try {
                entities.add(create());
            } catch (Exception ignored) {
            }
        }

        return entities;
    }

    protected int randomNum() {
        return rd.nextInt(9) + 1;
    }

    protected String randomNum(int length) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(randomNum());
        }

        return builder.toString();
    }
}
