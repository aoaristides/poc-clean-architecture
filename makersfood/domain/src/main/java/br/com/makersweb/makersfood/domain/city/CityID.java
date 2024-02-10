package br.com.makersweb.makersfood.domain.city;

import br.com.makersweb.makersfood.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class CityID extends Identifier {

    private final String value;

    private CityID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static CityID unique() {
        return CityID.from(UUID.randomUUID());
    }

    public static CityID from(final String anId) {
        return new CityID(anId);
    }

    public static CityID from(final UUID anId) {
        return new CityID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CityID cityID = (CityID) o;
        return Objects.equals(getValue(), cityID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
