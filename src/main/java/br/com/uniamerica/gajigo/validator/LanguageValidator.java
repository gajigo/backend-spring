package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Language;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;

public class LanguageValidator extends AbstractValidator<Language> {
    public LanguageValidator() {
        super(Language.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Language language = (Language) obj;

        ((Language) obj).setName(capitalizeString(language.getName()));
        validateName(language, errors);
    }

    private void validateName(Language language, Errors errors) {
        String name = language.getName();
        validateString("name", name, errors);
    }
}
