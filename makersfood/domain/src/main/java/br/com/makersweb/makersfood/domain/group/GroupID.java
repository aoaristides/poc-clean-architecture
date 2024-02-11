package br.com.makersweb.makersfood.domain.group;

import br.com.makersweb.makersfood.domain.Identifier;
import br.com.makersweb.makersfood.domain.utils.IdUtils;

import java.util.Objects;

/**
 * @author aaristides
 */
public class GroupID extends Identifier {

    private final String value;

    private GroupID(final String value) {
        this.value = Objects.requireNonNull(value);
    }

    public static GroupID unique() {
        return GroupID.from(IdUtils.uuid());
    }

    public static GroupID from(final String anId) {
        return new GroupID(anId);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupID groupID = (GroupID) o;
        return Objects.equals(getValue(), groupID.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
