package br.com.makersweb.makersfood.domain.validation;

import java.util.List;

/**
 * @author aaristides
 */
public interface ValidationHandler {

    ValidationHandler append(Error anError);

    ValidationHandler append(ValidationHandler anHandler);

    <T> T validate(Validation<T> aValidation);

    List<Error> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    default Error firstError() {
        if (getErrors() != null && !getErrors().isEmpty()) {
            return getErrors().getFirst();
        }

        return null;
    }

    interface Validation<T> {
        T validate();
    }
}
