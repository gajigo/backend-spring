package br.com.uniamerica.gajigo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

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
            return true;
        }

        return false;
    }

    protected Boolean validateNull(String fieldName, Object field, Errors errors) {
        return validateNull(fieldName, field,
                className + " " + fieldName + " must not be null!",
                errors);
    }

    protected void validateString(String fieldName, String field, Errors errors) {
        if (validateNull(fieldName, field, errors)) {
            // Cannot do any more validations if the field is null
            return;
        }

        if (field.isBlank()) {
            errors.rejectValue(fieldName,
                    className.toLowerCase() + ".empty",
                    className + " " + fieldName + " must not be empty!");
        }
    }
}
