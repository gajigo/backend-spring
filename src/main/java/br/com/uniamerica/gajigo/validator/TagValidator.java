package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Tag;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class TagValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Tag.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Tag tag = (Tag) obj;

        validateName(tag, errors);
    }

    private void validateName(Tag tag, Errors errors) {
        String name = tag.getName();

        if (name == null) {
            errors.rejectValue("name", "name.null",
                               "Tag name must not be null!");
        }

        if (name.isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Tag name must not be empty!");
        }
    }
}
