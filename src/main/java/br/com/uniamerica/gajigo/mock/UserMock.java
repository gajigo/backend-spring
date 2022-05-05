package br.com.uniamerica.gajigo.mock;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UserMock extends AbstractMock<User> {
    private CityRepository cityRepository;

    @Autowired
    public UserMock(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    public User create() {
        User user = new User();

        user.setName(generateName());
        user.setDescription(generateDescription());

        user.setUsername(generateUsername());
        user.setPassword(generatePassword());
        user.setEmail(generateEmail());

        user.setCpf(generateCpf());
        user.setTelephone(generateTelephone());

        user.setLocation(generateLocation());

        return user;
    }

    private String generateName() {
        return faker.name().name();
    }

    private String generateDescription() {
        return faker.lorem().sentence();
    }

    private String generateEmail() {
        return faker.lorem().characters(10) +
                "@gmail.com";
    }

    private String generateUsername() {
        return faker.name().username();
    }

    private String generatePassword() {
        return faker.lorem().characters(10);
    }

    private String generateCpf() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            builder.append(randomNum(3));
            builder.append(".");
        }

        builder.setLength(builder.length()-1);
        builder.append("-");
        builder.append(randomNum(2));

        return builder.toString();
    }

    private String generateTelephone() {
        StringBuilder builder = new StringBuilder();

        builder.append("+55(45) 9");
        builder.append(randomNum(4));
        builder.append("-");
        builder.append(randomNum(4));

        return builder.toString();
    }

    private City generateLocation() {
        List<City> cities = cityRepository.findAll();

        if (cities.size() == 0) return null;
        return cities.get(rd.nextInt(cities.size()));
    }
}
