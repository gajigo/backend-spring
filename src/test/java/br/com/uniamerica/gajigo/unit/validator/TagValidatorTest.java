package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Tag;
import br.com.uniamerica.gajigo.validator.TagValidator;
import org.junit.jupiter.api.Test;
import org.springframework.validation.Errors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TagValidatorTest extends AbstractValidatorTest<Tag> {
    public TagValidatorTest() {
        super(new TagValidator(), Tag.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Tag tag = new Tag();
        Errors errors = validator.validate(tag);

        assertTrue(errors.hasErrors());
    }

    @Test
    public void testEmptyName() throws Exception {
        Tag tag = validObject();
        tag.setName("");

        Errors errors = validator.validate(tag);

        assertEquals(1, errors.getErrorCount());
    }

    public Tag validObject() {
        Tag tag = new Tag("Test", "test");

        return tag;
    }
}
