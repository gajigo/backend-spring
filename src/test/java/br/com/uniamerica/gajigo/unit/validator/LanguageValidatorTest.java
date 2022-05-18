package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Language;
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
        Errors errors = validate(language);

        assert errors.hasErrors();
    }
}
