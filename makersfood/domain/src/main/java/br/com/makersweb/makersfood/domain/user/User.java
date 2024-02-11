package br.com.makersweb.makersfood.domain.user;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.group.GroupID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author aaristides
 */
public class User extends AggregateRoot<UserID> {

    private String name;
    private String mail;
    private String password;
    private boolean active;
    private List<GroupID> groups;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private User(
            final UserID userID,
            final String name,
            final String mail,
            final String password,
            final boolean active,
            final List<GroupID> groups,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(userID);
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.active = active;
        this.groups = groups;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
        this.deletedAt = deletedAt;
    }

    public static User newUser(
            final String name,
            final String mail,
            final String password,
            final boolean isActive
    ) {
        final var anId = UserID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = isActive ? null : now;
        return new User(anId, name, mail, password, isActive, new ArrayList<>(), now, now, deletedAt);
    }

    public static User with(
            final UserID userID,
            final String name,
            final String mail,
            final String password,
            final boolean active,
            final List<GroupID> groups,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        return new User(userID, name, mail, password, active, groups, createdAt, updatedAt, deletedAt);
    }

    public static User with(final User aUser) {
        return with(
                aUser.getId(),
                aUser.name,
                aUser.mail,
                aUser.password,
                aUser.active,
                new ArrayList<>(aUser.groups),
                aUser.createdAt,
                aUser.updatedAt,
                aUser.deletedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {

    }

    public User update(
            final String name,
            final String mail,
            final String password,
            final boolean isActive,
            final List<GroupID> groups
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = name;
        this.mail = mail;
        this.password = password;
        this.groups = new ArrayList<>(groups != null ? groups : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    public User deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User addGroup(final GroupID groupID) {
        if (groupID == null) {
            return this;
        }
        this.groups.add(groupID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User addGroups(final List<GroupID> groups) {
        if (groups == null || groups.isEmpty()) {
            return this;
        }
        this.groups.addAll(groups);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public User removeGroup(final GroupID groupID) {
        if (groupID == null) {
            return this;
        }
        this.groups.remove(groupID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate User", notification);
        }
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public boolean isActive() {
        return active;
    }

    public List<GroupID> getGroups() {
        return groups;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }
}
