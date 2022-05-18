package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Document;
import br.com.uniamerica.gajigo.validator.AbstractValidator;
import br.com.uniamerica.gajigo.validator.DocumentValidator;
import org.junit.Test;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;

public class DocumentValidatorTest extends AbstractValidatorTest<Document> {
    public DocumentValidatorTest() {
        super(new DocumentValidator(), Document.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Document document = new Document();
        Errors errors = validate(document);

        assert errors.hasErrors();
    }
}
