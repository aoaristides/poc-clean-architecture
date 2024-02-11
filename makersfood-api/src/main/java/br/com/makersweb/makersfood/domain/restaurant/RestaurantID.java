package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class RestaurantID extends Identifier {

    private final String value;

    private RestaurantID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RestaurantID unique() {
        return RestaurantID.from(IdUtils.uuid());
    }

    public static RestaurantID from(final String anId) {
        return new RestaurantID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RestaurantID that = (RestaurantID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
