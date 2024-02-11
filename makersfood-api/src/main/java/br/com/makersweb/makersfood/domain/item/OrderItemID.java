package br.com.makersweb.makersfood.domain.item;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class OrderItemID extends Identifier {

    private final String value;

    private OrderItemID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static OrderItemID unique() {
        return OrderItemID.from(IdUtils.uuid());
    }

    public static OrderItemID from(final String anId) {
        return new OrderItemID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItemID that = (OrderItemID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
