package br.com.makersweb.makersfood.domain.permission;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class PermissionID extends Identifier {

    private final String value;

    private PermissionID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static PermissionID unique() {
        return PermissionID.from(IdUtils.uuid());
    }

    public static PermissionID from(final String anId) {
        return new PermissionID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PermissionID that = (PermissionID) o;
        return Objects.equals(getValue(), that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
