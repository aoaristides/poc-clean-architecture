package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

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
        final var now = Instant.now();
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
