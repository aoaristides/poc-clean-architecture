package br.com.makersweb.makersfood.domain.permission;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.time.Instant;

/**
 * @author aaristides
 */
public class Permission extends AggregateRoot<PermissionID> {

    private String name;
    private String description;
    private Instant createdAt;
    private Instant updatedAt;

    private Permission(
            final PermissionID permissionID,
            final String name,
            final String description,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(permissionID);
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Permission newPermission(final String name, final String description) {
        final var anId = PermissionID.unique();
        final var now = InstantUtils.now();
        return new Permission(anId, name, description, now, now);
    }

    public static Permission with(
            final PermissionID permissionID,
            final String name,
            final String description,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Permission(permissionID, name, description, createdAt, updatedAt);
    }

    public static Permission with(final Permission aPermission) {
        return with(
                aPermission.getId(),
                aPermission.name,
                aPermission.description,
                aPermission.createdAt,
                aPermission.updatedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new PermissionValidator(this, handler).validate();
    }

    public Permission update(final String name, final String description) {
        this.name = name;
        this.description = description;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
