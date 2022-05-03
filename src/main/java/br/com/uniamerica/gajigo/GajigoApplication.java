package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.validator.CountryValidator;
import br.com.uniamerica.gajigo.validator.UserValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@SpringBootApplication
public class GajigoApplication implements RepositoryRestConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(GajigoApplication.class, args);
    }

    @Override
    public void configureValidatingRepositoryEventListener(
            ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", new CountryValidator());
        validatingListener.addValidator("beforeCreate", new UserValidator());
    }
}
