package br.com.makersweb.makersfood.domain.payment;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Payment extends AggregateRoot<PaymentID> {

    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    private Payment(
            final PaymentID paymentID,
            final String description,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(paymentID);
        this.description = description;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Payment newPayment(final String description) {
        final var anId = PaymentID.unique();
        final var now = InstantUtils.now();
        return new Payment(anId, description, now, now);
    }

    public static Payment with(
            final PaymentID paymentID,
            final String description,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Payment(paymentID, description, createdAt, updatedAt);
    }

    public static Payment with(final Payment aPayment) {
        return with(aPayment.getId(), aPayment.description, aPayment.createdAt, aPayment.updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PaymentValidator(this, handler).validate();
    }

    public Payment update(final String description) {
        this.description = description;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
