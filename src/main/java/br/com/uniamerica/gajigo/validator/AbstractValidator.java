package br.com.uniamerica.gajigo.validator;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindException;
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

    public Errors validate(T obj) {
        Errors errors = new BindException(obj, className);

        validate(obj, errors);

        return errors;
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

        builder.setLength(builder.length() - 1);
        return builder.toString();
    }

}
