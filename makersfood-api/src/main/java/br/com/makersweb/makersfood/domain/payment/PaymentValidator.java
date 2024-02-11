package br.com.makersweb.makersfood.domain.payment;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

/**
 * @author aaristides
 */
public class PaymentValidator extends Validator {

    private final Payment payment;

    public static final int NAME_MAX_LENGTH = 255;
    public static final int NAME_MIN_LENGTH = 3;

    public PaymentValidator(final Payment payment, final ValidationHandler handler) {
        super(handler);
        this.payment = payment;
    }

    @Override
    public void validate() {
        checkDescriptionConstraints();
    }

    private void checkDescriptionConstraints() {
        final var description = this.payment.getDescription();
        if (description == null) {
            this.validationHandler().append(new Error("'description' should not be null"));
            return;
        }

        if (description.isBlank()) {
            this.validationHandler().append(new Error("'description' should not be empty"));
            return;
        }

        final var length = description.trim().length();
        if (length > NAME_MAX_LENGTH || length < NAME_MIN_LENGTH) {
            this.validationHandler().append(new Error("'description' must be between 3 and 255 characters"));
        }
    }
}
