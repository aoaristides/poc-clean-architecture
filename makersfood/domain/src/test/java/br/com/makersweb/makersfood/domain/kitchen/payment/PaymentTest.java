package br.com.makersweb.makersfood.domain.kitchen.payment;

import br.com.makersweb.makersfood.domain.payment.Payment;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class PaymentTest {

    @Test
    public void givenAValidParams_whenCallNewPayment_thenInstantiateACity() {
        final var expectedDescription = "Admin";

        final var payment = Payment.newPayment(expectedDescription);

        Assertions.assertNotNull(payment);
        Assertions.assertNotNull(payment.getId());
        Assertions.assertEquals(expectedDescription, payment.getDescription());
        Assertions.assertNotNull(payment.getCreatedAt());
        Assertions.assertNotNull(payment.getUpdatedAt());
    }

}
