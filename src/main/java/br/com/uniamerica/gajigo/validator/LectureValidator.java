package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.validation.Errors;

public class LectureValidator extends AbstractValidator<Lecture> {
    public LectureValidator() {
        super(Lecture.class);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Lecture lecture = (Lecture) obj;

        validateName(lecture, errors);
    }

    private void validateName(Lecture lecture, Errors errors) {
        String name = lecture.getName();
        validateString("name", name, errors);
    }
}
