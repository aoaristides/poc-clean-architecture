package br.com.makersweb.makersfood.domain.payment;

import br.com.makersweb.makersfood.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class PaymentID extends Identifier {

    private final String value;

    private PaymentID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PaymentID unique() {
        return PaymentID.from(UUID.randomUUID());
    }

    public static PaymentID from(final String anId) {
        return new PaymentID(anId);
    }

    public static PaymentID from(final UUID anId) {
        return new PaymentID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentID paymentID = (PaymentID) o;
        return Objects.equals(getValue(), paymentID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
