package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Language;
import br.com.uniamerica.gajigo.validator.LanguageValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LanguageValidatorTest extends AbstractValidatorTest<Language> {
    public LanguageValidatorTest() {
        super(new LanguageValidator(), Language.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Language language = new Language();
        Errors errors = validator.validate(language);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        Language language = validObject();
        language.setName("");

        Errors errors = validator.validate(language);

        assertEquals(1, errors.getErrorCount());
    }

    public Language validObject() {
        Language language = new Language("Test");

        return language;
    }
}
