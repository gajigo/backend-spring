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

        checkNameNotBlank(tag, errors);
    }

    private void checkNameNotBlank(Tag tag, Errors errors) {
        if (tag.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Tag name must not be empty!");
        }
    }
}
