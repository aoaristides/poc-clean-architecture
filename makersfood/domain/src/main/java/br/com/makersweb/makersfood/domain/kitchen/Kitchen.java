package br.com.makersweb.makersfood.domain.kitchen;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.restaurant.RestaurantID;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Kitchen extends AggregateRoot<KitchenID> {

    private String name;
    private List<RestaurantID> restaurants;
    private Instant createdAt;
    private Instant updatedAt;

    private Kitchen(
            final KitchenID kitchenID,
            final String name,
            final List<RestaurantID> restaurants,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(kitchenID);
        this.name = name;
        this.restaurants = restaurants;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Kitchen newKitchen(final String name) {
        final var anId = KitchenID.unique();
        final var now = Instant.now();
        return new Kitchen(anId, name, new ArrayList<>(), now, now);
    }

    public static Kitchen with(
            final KitchenID kitchenID,
            final String name,
            final List<RestaurantID> restaurants,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Kitchen(kitchenID, name, restaurants, createdAt, updatedAt);
    }

    public static Kitchen with(final Kitchen aKitchen) {
        return with(aKitchen.getId(), aKitchen.name, aKitchen.restaurants, aKitchen.createdAt, aKitchen.updatedAt);
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new KitchenValidator(this, handler).validate();
    }

    public Kitchen update(final String name, final List<RestaurantID> restaurants) {
        this.name = name;
        this.restaurants = new ArrayList<>(restaurants != null ? restaurants : Collections.emptyList());
        this.updatedAt = Instant.now();
        return this;
    }

    public Kitchen addRestaurant(final RestaurantID restaurantID) {
        if (restaurantID == null) {
            return this;
        }
        this.restaurants.add(restaurantID);
        this.updatedAt = Instant.now();
        return this;
    }

    public Kitchen addRestaurants(final List<RestaurantID> restaurants) {
        if (restaurants == null || restaurants.isEmpty()) {
            return this;
        }
        this.restaurants.addAll(restaurants);
        this.updatedAt = Instant.now();
        return this;
    }

    public Kitchen removeRestaurant(final RestaurantID restaurantID) {
        if (restaurantID == null) {
            return this;
        }
        this.restaurants.remove(restaurantID);
        this.updatedAt = Instant.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public List<RestaurantID> getRestaurants() {
        return Collections.unmodifiableList(restaurants);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
