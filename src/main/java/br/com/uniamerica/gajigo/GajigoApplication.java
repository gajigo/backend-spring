package br.com.uniamerica.gajigo;

import br.com.uniamerica.gajigo.utils.ProjectionGenerator;
import br.com.uniamerica.gajigo.validator.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.event.ValidatingRepositoryEventListener;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.validation.Validator;

@SpringBootApplication
public class GajigoApplication implements RepositoryRestConfigurer {
    public static void main(String[] args) {
        ProjectionGenerator.generate();
        SpringApplication.run(GajigoApplication.class, args);
    }

    @Override
    public void configureValidatingRepositoryEventListener(
            ValidatingRepositoryEventListener validatingListener) {
        addValidator(new CityValidator(), validatingListener);
        addValidator(new CountryValidator(), validatingListener);
        addValidator(new DocumentValidator(), validatingListener);
        addValidator(new EventValidator(), validatingListener);
        addValidator(new LanguageValidator(), validatingListener);
        addValidator(new LectureValidator(), validatingListener);
        addValidator(new RoomValidator(), validatingListener);
        addValidator(new StateValidator(), validatingListener);
        addValidator(new TagValidator(), validatingListener);
        addValidator(new UserValidator(), validatingListener);
    }

    private void addValidator(Validator validator,
                              ValidatingRepositoryEventListener validatingListener) {
        validatingListener.addValidator("beforeCreate", validator);
        validatingListener.addValidator("beforeSave", validator);
        validatingListener.addValidator("beforeLinkSave", validator);
    }
}
