package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.City;
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
        Errors errors = validator.validate(tag);

        assert errors.hasErrors();
    }

    @Test
    public void testEmptyName() throws Exception {
        Tag tag = validObject();
        tag.setName("");

        Errors errors = validator.validate(tag);

        assert errors.getErrorCount() == 1;
    }

    public Tag validObject() {
        Tag tag = new Tag("Test", "test");

        return tag;
    }
}
