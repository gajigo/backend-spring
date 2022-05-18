package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.*;
import br.com.uniamerica.gajigo.validator.LanguageValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class LanguageValidatorTest extends AbstractValidatorTest<Language> {
    public LanguageValidatorTest() {
        super(new LanguageValidator(), Language.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Language language = new Language();
        Errors errors = validator.validate(language);

        assert errors.hasErrors();
    }

    @Test
    public void testEmptyName() throws Exception {
        Language language = validObject();
        language.setName("");

        Errors errors = validator.validate(language);

        assert errors.getErrorCount() == 1;
    }

    public Language validObject() {
        Language language = new Language("Test");

        return language;
    }
}
