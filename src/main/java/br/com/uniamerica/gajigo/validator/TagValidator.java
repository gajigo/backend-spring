package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Tag;
import org.springframework.validation.Errors;

public class TagValidator extends AbstractValidator<Tag> {
    public TagValidator() {
        super(Tag.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Tag tag = (Tag) obj;

        validateName(tag, errors);
    }

    private void validateName(Tag tag, Errors errors) {
        String name = tag.getName();
        if (!validateString("name", name, errors)) {
            return;
        }

        tag.setName(capitalizeString(tag.getName()));
    }
}
