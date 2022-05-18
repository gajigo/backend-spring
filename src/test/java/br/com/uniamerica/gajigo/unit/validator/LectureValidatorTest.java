package br.com.uniamerica.gajigo.unit.validator;

import br.com.uniamerica.gajigo.entity.Lecture;
import br.com.uniamerica.gajigo.validator.LectureValidator;
import org.junit.Test;
import org.springframework.validation.Errors;

public class LectureValidatorTest extends AbstractValidatorTest<Lecture> {
    public LectureValidatorTest() {
        super(new LectureValidator(), Lecture.class);
    }

    @Test
    public void testEmptyObject() throws Exception {
        Lecture lecture = new Lecture();
        Errors errors = validate(lecture);

        assert errors.hasErrors();
    }
}
