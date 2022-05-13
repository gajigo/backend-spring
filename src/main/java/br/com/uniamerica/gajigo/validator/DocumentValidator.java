package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Document;
import br.com.uniamerica.gajigo.entity.State;
import org.springframework.validation.Errors;

public class DocumentValidator extends AbstractValidator<Document> {
    public DocumentValidator() {
        super(Document.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Document document = (Document) obj;

        validateType(document, errors);
        validateValue(document, errors);
    }

    private void validateType(Document document, Errors errors) {
        String type = document.getType().toLowerCase();
        if (!validateString("type", type, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        document.setType(type);

        int document_repeats = 0;
        for (Document doc : document.getUser().getDocuments()) {
            if (doc.getType() == type) {
                document_repeats++;
            }
        }

        // check by ID instead, we shouldnt have 2 document repeats ever because the document isnt sent to the list at this point
        if ((document.getUpdated() == null && document_repeats == 1) ||
             document_repeats == 2) {
            errors.rejectValue("type", "type.duplicate",
                    "User can only have one document of each type!");
        }
    }

    private void validateValue(Document document, Errors errors) {
    }
}
