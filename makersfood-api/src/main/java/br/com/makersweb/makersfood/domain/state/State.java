package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;

import java.time.Instant;
import java.util.Objects;

/**
 * @author aaristides
 */
public class State extends AggregateRoot<StateID> {

    private String name;
    private Instant createdAt;
    private Instant updatedAt;

    private State(
            final StateID stateID,
            final String name,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(stateID);
        this.name = name;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static State newState(
            final String name
    ) {
        final var anId = StateID.unique();
        final var now = InstantUtils.now();
        return new State(anId, name, now, now);
    }

    public static State with(
            final StateID stateID,
            final String name,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new State(stateID, name, createdAt, updatedAt);
    }

    public static State with(final State aState) {
        return with(aState.getId(), aState.name, aState.createdAt, aState.updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new StateValidator(this, handler).validate();
    }

    public State update(final String name) {
        this.name = name;
        this.updatedAt = InstantUtils.now();
        selfValidate();
        return this;
    }

    private void selfValidate() {
        final var notification = Notification.create();
        validate(notification);

        if (notification.hasError()) {
            throw new NotificationException("Failed to create a Aggregate State", notification);
        }
    }

    public String getName() {
        return name;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
