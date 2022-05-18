package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
import br.com.uniamerica.gajigo.entity.Document;
import br.com.uniamerica.gajigo.entity.User;
import br.com.uniamerica.gajigo.validator.DocumentValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

import java.util.HashSet;

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

    @Test
    public void testEmptyType() throws Exception {
        Document document = validObject();
        document.setType("");

        Errors errors = validate(document);

        assert errors.getErrorCount() == 1;
    }

    @Test
    public void testInvalidCPF() throws Exception {
        Document document = validObject();
        document.setType("cpf");
        document.setValue("asdfsfaasf");

        Errors errors = validate(document);

        assert errors.getErrorCount() == 1;
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
