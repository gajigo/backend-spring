package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Document;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.validator.DocumentValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DocumentValidatorTest extends AbstractValidatorTest<Document> {
    public DocumentValidatorTest() {
        super(new DocumentValidator(), Document.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Document document = new Document();
        Errors errors = validator.validate(document);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyType() throws Exception {
        Document document = validObject();
        document.setType("");

        Errors errors = validator.validate(document);

        assertEquals(1, errors.getErrorCount());
    }

    @Test
    public void testInvalidCPF() throws Exception {
        Document document = validObject();
        document.setType("cpf");
        document.setValue("asdfsfaasf");

        Errors errors = validator.validate(document);

        assertEquals(1, errors.getErrorCount());
    }

    public Document validObject() {
        Document document = new Document("cpf", "953.558.133-30");
        User user = new UserValidatorTest().validObject();
        document.setUser(user);
        user.setDocuments(new HashSet<>());
        user.getDocuments().add(document);

        return document;
    }
}
