package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Language;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LanguageValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Language.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Language language = (Language) obj;

        validateName(language, errors);
    }

    private void validateName(Language language, Errors errors) {
        String name = language.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "Language name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Language name must not be empty!");
        }
    }
}
