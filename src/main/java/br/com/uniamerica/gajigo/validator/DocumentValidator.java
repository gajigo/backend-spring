package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Document;
import br.com.uniamerica.gajigo.entity.User;
import org.springframework.validation.Errors;

import java.util.regex.Pattern;

public class DocumentValidator extends AbstractValidator<Document> {
    public DocumentValidator() {
        super(Document.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Document document = (Document) obj;

        validateUser(document, errors);
        validateType(document, errors);
        validateValue(document, errors);
    }

    private void validateUser(Document document, Errors errors) {
        User user = document.getUser();

        validateNull("user", user,
                "Document must belong to an user!", errors);
    }

    private void validateType(Document document, Errors errors) {
        String type = document.getType();
        if (!validateString("type", type, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        type = type.toLowerCase();
        document.setType(type);

        User user = document.getUser();
        if (user == null) {
            // Can't check for duplicates in user if the user doesn't exist
            return;
        }

        for (Document doc : user.getDocuments()) {
            // TODO Confirm later if updating a document gets past this validation as it should
            // Only real way to test this is through integration testing because the unit tests don't set ID
            if (doc.getType() == type && doc.getId() != document.getId()) {
                errors.rejectValue("type", "type.duplicate",
                        "User can only have one document of each type!");
            }
        }
    }

    private void validateValue(Document document, Errors errors) {
        String value = document.getValue();
        if (!validateString("value", value, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        value = value.trim();
        document.setValue(value);

        String type = document.getType();
        switch (type) {
            case "cpf":
                // regex = ^\d{3}\.\d{3}\.\d{3}-\d{2}$, mask = ddd.ddd.ddd-dd
                Pattern pattern = Pattern.compile("^\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}$");
                Boolean matches = pattern.matcher(value).find();
                if (!matches) {
                    errors.rejectValue("value", "value.cpfinvalid",
                            "Provided CPF invalid! Expected mask: ddd.ddd.ddd-dd");
                }

                // TODO add checksum validation
                break;
        }
    }
}
