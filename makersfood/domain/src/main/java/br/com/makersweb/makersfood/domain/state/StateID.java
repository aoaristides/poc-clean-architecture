package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.Identifier;

import java.util.Objects;
import java.util.UUID;

/**
 * @author aaristides
 */
public class StateID extends Identifier {

    private final String value;

    private StateID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static StateID unique() {
        return StateID.from(UUID.randomUUID());
    }

    public static StateID from(final String anId) {
        return new StateID(anId);
    }

    public static StateID from(final UUID anId) {
        return new StateID(anId.toString().toLowerCase());
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StateID stateID = (StateID) o;
        return Objects.equals(getValue(), stateID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
