package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Tag;
import br.com.uniamerica.gajigo.validator.TagValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class TagValidatorTest extends AbstractValidatorTest<Tag> {
    public TagValidatorTest() {
        super(new TagValidator(), Tag.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Tag tag = new Tag();
        Errors errors = validate(tag);

        assert errors.hasErrors();
    }
}
