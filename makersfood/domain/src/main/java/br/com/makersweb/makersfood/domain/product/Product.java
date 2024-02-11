package br.com.makersweb.makersfood.domain.product;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.restaurant.RestaurantID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Product extends AggregateRoot<ProductID> {

    private String name;
    private String description;
    private BigDecimal price;
    private boolean active;
    private RestaurantID restaurant;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    private Product(
            final ProductID productID,
            final String name,
            final String description,
            final BigDecimal price,
            final boolean active,
            final RestaurantID restaurant,
            final Instant createdAt,
            final Instant updatedAt,
            final Instant deletedAt
    ) {
        super(productID);
        this.name = name;
        this.description = description;
        this.price = price;
        this.active = active;
        this.restaurant = restaurant;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
        this.deletedAt = deletedAt;
    }

    public static Product newProduct(
            final String name,
            final String description,
            final BigDecimal price,
            final boolean active
    ) {
        final var anId = ProductID.unique();
        final var now = InstantUtils.now();
        final var deletedAt = active ? null : now;
        return new Product(anId, name, description, price, active, null, now, deletedAt, now);
    }

    public static Product with(
            final ProductID productID,
            final String name,
            final String description,
            final BigDecimal price,
            final boolean active,
            final RestaurantID restaurant,
            final Instant createdAt,
            final Instant deletedAt,
            final Instant updatedAt
    ) {
        return new Product(productID, name, description, price, active, restaurant, createdAt, deletedAt, updatedAt);
    }

    public static Product with(final Product aProduct) {
        return with(
                aProduct.getId(),
                aProduct.name,
                aProduct.description,
                aProduct.price,
                aProduct.active,
                aProduct.restaurant,
                aProduct.createdAt,
                aProduct.deletedAt,
                aProduct.updatedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new ProductValidator(this, handler).validate();
    }

    public Product update(
            final String name,
            final String description,
            final BigDecimal price,
            final boolean isActive,
            final RestaurantID restaurantID
    ) {
        if (isActive) {
            activate();
        } else {
            deactivate();
        }
        this.name = name;
        this.description = description;
        this.price = price;
        this.restaurant = restaurantID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Product activate() {
        this.deletedAt = null;
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Product deactivate() {
        if (getDeletedAt() == null) {
            this.deletedAt = InstantUtils.now();
        }
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean isActive() {
        return active;
    }

    public RestaurantID getRestaurant() {
        return restaurant;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getDeletedAt() {
        return deletedAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
