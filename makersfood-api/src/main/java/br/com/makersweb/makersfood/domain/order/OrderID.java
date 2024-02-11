package br.com.makersweb.makersfood.domain.order;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class OrderID extends Identifier {

    private final String value;

    private OrderID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static OrderID unique() {
        return OrderID.from(IdUtils.uuid());
    }

    public static OrderID from(final String anId) {
        return new OrderID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderID orderID = (OrderID) o;
        return Objects.equals(getValue(), orderID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
