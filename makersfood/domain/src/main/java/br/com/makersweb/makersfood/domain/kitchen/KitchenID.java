package br.com.makersweb.makersfood.domain.kitchen;

import br.com.makersweb.makersfood.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class KitchenID extends Identifier {

    private final String value;

    private KitchenID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static KitchenID unique() {
        return KitchenID.from(UUID.randomUUID());
    }

    public static KitchenID from(final String anId) {
        return new KitchenID(anId);
    }

    public static KitchenID from(final UUID anId) {
        return new KitchenID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KitchenID kitchenID = (KitchenID) o;
        return Objects.equals(getValue(), kitchenID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
