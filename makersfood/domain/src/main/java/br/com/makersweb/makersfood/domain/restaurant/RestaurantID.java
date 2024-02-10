package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class RestaurantID extends Identifier {

    private final String value;

    private RestaurantID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static RestaurantID unique() {
        return RestaurantID.from(UUID.randomUUID());
    }

    public static RestaurantID from(final String anId) {
        return new RestaurantID(anId);
    }

    public static RestaurantID from(final UUID anId) {
        return new RestaurantID(anId.toString().toLowerCase());
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
