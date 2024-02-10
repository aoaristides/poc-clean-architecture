package br.com.makersweb.makersfood.domain.city;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.state.StateID;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.Objects;

/**
 * @author aaristides
 */
public class City extends AggregateRoot<CityID> {

    private String name;
    private StateID state;
    private Instant createdAt;
    private Instant updatedAt;

    private City(
            final CityID cityID,
            final String name,
            final StateID state,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(cityID);
        this.name = name;
        this.state = state;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static City newCity(final String name) {
        final var anId = CityID.unique();
        final var now = Instant.now();
        return new City(anId, name, null, now, now);
    }

    public static City with(
            final CityID cityID,
            final String name,
            final StateID state,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new City(cityID, name, state, createdAt, updatedAt);
    }

    public static City with(final City aCity) {
        return with(aCity.getId(), aCity.name, aCity.state, aCity.createdAt, aCity.updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new CityValidator(this, handler).validate();
    }

    public City addState(final StateID state) {
        if (Objects.isNull(state)) {
            return this;
        }
        this.state = state;
        this.updatedAt = Instant.now();
        return this;
    }

    public City update(final String name, final StateID state) {
        this.name = name;
        this.state = state;
        this.updatedAt = Instant.now();
        return this;
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
