package br.com.makersweb.makersfood.domain.item;

import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.Validator;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class OrderItemValidator extends Validator {

    private final OrderItem orderItem;

    public OrderItemValidator(final OrderItem orderItem, final ValidationHandler handler) {
        super(handler);
        this.orderItem = orderItem;
    }

    @Override
    public void validate() {
        checkUnitPriceConstraints();
        checkTotalPriceConstraints();
        checkQuantityConstraints();
    }

    private void checkUnitPriceConstraints() {
        final var unitPrice = this.orderItem.getUnitPrice();
        if (unitPrice == null) {
            this.validationHandler().append(new Error("'unitPrice' should not be null"));
            return;
        }

        if (unitPrice.compareTo(BigDecimal.ZERO) < 0) {
            this.validationHandler().append(new Error("'unitPrice' cannot be less than zero"));
        }
    }

    private void checkTotalPriceConstraints() {
        final var totalPrice = this.orderItem.getTotalPrice();
        if (totalPrice == null) {
            this.validationHandler().append(new Error("'totalPrice' should not be null"));
            return;
        }

        if (totalPrice.compareTo(BigDecimal.ZERO) < 0) {
            this.validationHandler().append(new Error("'totalPrice' cannot be less than zero"));
        }
    }

    private void checkQuantityConstraints() {
        final var quantity = this.orderItem.getQuantity();
        if (quantity == null) {
            this.validationHandler().append(new Error("'quantity' should not be null"));
            return;
        }

        if (quantity < 0) {
            this.validationHandler().append(new Error("'quantity' cannot be less than zero"));
        }
    }
}
