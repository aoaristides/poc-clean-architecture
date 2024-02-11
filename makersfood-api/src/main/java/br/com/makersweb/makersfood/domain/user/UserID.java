package br.com.makersweb.makersfood.domain.user;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class UserID extends Identifier {

    private final String value;

    private UserID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static UserID unique() {
        return UserID.from(IdUtils.uuid());
    }

    public static UserID from(final String anId) {
        return new UserID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserID userID = (UserID) o;
        return Objects.equals(getValue(), userID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
