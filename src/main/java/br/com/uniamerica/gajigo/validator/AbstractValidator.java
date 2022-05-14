package br.com.uniamerica.gajigo.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.time.LocalDateTime;

public abstract class AbstractValidator<T> implements Validator {
    private final Class<T> objType;
    private final String className;

    public AbstractValidator(Class<T> objType) {
        this.objType = objType;
        this.className = objType.getSimpleName();
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return objType.equals(clazz);
    }

    protected Boolean validateNull(String fieldName, Object field, String message, Errors errors) {
        if (field == null) {
            errors.rejectValue(fieldName,
                    className.toLowerCase() + ".null",
                    message);
            return false;
        }

        return true;
    }

    protected Boolean validateNull(String fieldName, Object field, Errors errors) {
        return validateNull(fieldName, field,
                className + " " + fieldName + " must not be null!",
                errors);
    }

    protected Boolean validateString(String fieldName, String field, Errors errors) {
        if (!validateNull(fieldName, field, errors)) {
            // Cannot do any more validations if the field is null
            return false;
        }

        if (field.isBlank()) {
            errors.rejectValue(fieldName,
                    className.toLowerCase() + ".empty",
                    className + " " + fieldName + " must not be empty!");
            return false;
        }

        return true;
    }

    protected String capitalizeString(String text) {
        StringBuilder builder = new StringBuilder();

        for (String s : text.split(" ")) {
            builder.append(StringUtils.capitalize(s) + " ");
        }

        builder.setLength(builder.length()-1);
        return builder.toString();
    }

    protected boolean intervalOverlaps(LocalDateTime start1, LocalDateTime end1,
                                       LocalDateTime start2, LocalDateTime end2) {
        return start1.isBefore(end2) && start2.isBefore(end1);
    }
}
