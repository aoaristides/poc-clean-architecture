package br.com.makersweb.makersfood.domain.product;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class ProductID extends Identifier {

    private final String value;

    private ProductID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static ProductID unique() {
        return ProductID.from(IdUtils.uuid());
    }

    public static ProductID from(final String anId) {
        return new ProductID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID productID = (ProductID) o;
        return Objects.equals(getValue(), productID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
