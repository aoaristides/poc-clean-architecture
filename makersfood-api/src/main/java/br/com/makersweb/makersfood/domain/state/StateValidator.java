package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

/**
 * @author aaristides
 */
public class StateValidator extends Validator {

    private final State state;

    public static final int NAME_MAX_LENGTH = 2;

    public StateValidator(final State state, final ValidationHandler handler) {
        super(handler);
        this.state = state;
    }

    @Override
    public void validate() {
        checkNameConstrains();
    }

    private void checkNameConstrains() {
        final var name = this.state.getName();
        if (name == null) {
            this.validationHandler().append(new Error("'name' should not be null"));
            return;
        }

        if (name.isBlank()) {
            this.validationHandler().append(new Error("'name' should not be empty"));
            return;
        }

        final var length = name.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MAX_LENGTH) {
            this.validationHandler().append(new Error("'name' must have 2 characters"));
        }
    }
}
