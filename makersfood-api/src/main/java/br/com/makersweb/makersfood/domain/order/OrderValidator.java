package br.com.makersweb.makersfood.domain.order;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

/**
 * @author aaristides
 */
public class OrderValidator extends Validator {

    private final Order order;

    public static final int CODE_MAX_LENGTH = 255;
    public static final int CODE_MIN_LENGTH = 3;

    public OrderValidator(final Order order, final ValidationHandler handler) {
        super(handler);
        this.order = order;
    }

    @Override
    public void validate() {
        checkNameConstraints();
    }

    private void checkNameConstraints() {
        final var code = this.order.getCode();
        if (code == null) {
            this.validationHandler().append(new Error("'code' should not be null"));
            return;
        }

        if (code.isBlank()) {
            this.validationHandler().append(new Error("'code' should not be empty"));
            return;
        }

        final var length = code.trim().length();
        if (length > CODE_MAX_LENGTH || length < CODE_MIN_LENGTH) {
            this.validationHandler().append(new Error("'code' must be between 3 and 255 characters"));
        }
    }
}
