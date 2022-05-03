package br.com.uniamerica.gajigo.validator;

import br.com.uniamerica.gajigo.entity.Lecture;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LectureValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Lecture.class.equals(clazz);
    }

    @Override
    public void validate(Object obj, Errors errors) {
        Lecture lecture = (Lecture) obj;

        checkNameNotBlank(lecture, errors);
    }

    private void checkNameNotBlank(Lecture lecture, Errors errors) {
        if (lecture.getName().isBlank()) {
            errors.rejectValue("name", "name.empty",
                               "Lecture name must not be empty!");
        }
    }
}
