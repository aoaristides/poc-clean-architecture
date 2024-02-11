package br.com.makersweb.makersfood.domain.city;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

/**
 * @author aaristides
 */
public class CityValidator extends Validator {

    private final City city;

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    public CityValidator(final City city, final ValidationHandler handler) {
        super(handler);
        this.city = city;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var name = this.city.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'name' must be between 3 and 255 characters"));
        }
    }
}
