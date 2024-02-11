package br.com.makersweb.makersfood.domain.group;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.permission.PermissionID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author aaristides
 */
public class Group extends AggregateRoot<GroupID> {

    private String name;
    private List<PermissionID> permissions;
    private Instant createdAt;
    private Instant updatedAt;

    private Group(
            final GroupID groupID,
            final String name,
            final List<PermissionID> permissions,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(groupID);
        this.name = name;
        this.permissions = permissions;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Group newGroup(final String name) {
        final var anId = GroupID.unique();
        final var now = InstantUtils.now();
        return new Group(anId, name, new ArrayList<>(), now, now);
    }

    public static Group with(
            final GroupID groupID,
            final String name,
            final List<PermissionID> permissions,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Group(groupID, name, permissions, createdAt, updatedAt);
    }

    public static Group with(final Group aGroup) {
        return with(
                aGroup.getId(),
                aGroup.name,
                new ArrayList<>(aGroup.permissions),
                aGroup.createdAt,
                aGroup.updatedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new GroupValidator(this, handler).validate();
    }

    public Group update(final String name, final List<PermissionID> permissions) {
        this.name = name;
        this.permissions = new ArrayList<>(permissions != null ? permissions : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Group addPermission(final PermissionID permissionID) {
        if (permissionID == null) {
            return this;
        }
        this.permissions.add(permissionID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Group addPermissions(final List<PermissionID> permissions) {
        if (permissions == null || permissions.isEmpty()) {
            return this;
        }
        this.permissions.addAll(permissions);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Group removePermission(final PermissionID permissionID) {
        if (permissionID == null) {
            return this;
        }
        this.permissions.remove(permissionID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public List<PermissionID> getPermissions() {
        return Collections.unmodifiableList(permissions);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
