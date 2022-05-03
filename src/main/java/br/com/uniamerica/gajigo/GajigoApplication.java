package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.validator.CountryValidator;
import br.com.uniamerica.gajigo.validator.UserValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;

@SpringBootApplication
public class GajigoApplication implements RepositoryRestConfigurer {
    public static void main(String[] args) {
        SpringApplication.run(GajigoApplication.class, args);
    }

    @Override
    public void configureValidatingRepositoryEventListener(
            ValidatingRepositoryEventListener validatingListener) {
        addValidator(new CountryValidator(), validatingListener);
        addValidator(new UserValidator(), validatingListener);
    }

    private void addValidator(Validator validator,
                              ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
    }
}
