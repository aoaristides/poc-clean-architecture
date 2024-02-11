package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.kitchen.KitchenID;
import br.com.makersweb.makersfood.domain.payment.PaymentID;
import br.com.makersweb.makersfood.domain.product.ProductID;
import br.com.makersweb.makersfood.domain.user.UserID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author aaristides
 */
public class Restaurant extends AggregateRoot<RestaurantID> {

    private String name;
    private BigDecimal freightRate;
    private KitchenID kitchen;
    private Address address;
    private boolean active;
    private boolean open;
    private List<PaymentID> payments;
    private List<ProductID> products;
    private List<UserID> responsibles;
    private Instant createdAt;
    private Instant updatedAt;

    private Restaurant(
            final RestaurantID restaurantID,
            final String name,
            final BigDecimal freightRate,
            final KitchenID kitchen,
            final Address address,
            final boolean active,
            final boolean open,
            final List<PaymentID> payments,
            final List<ProductID> products,
            final List<UserID> responsibles,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(restaurantID);
        this.name = name;
        this.freightRate = freightRate;
        this.kitchen = kitchen;
        this.address = address;
        this.active = active;
        this.open = open;
        this.payments = payments;
        this.products = products;
        this.responsibles = responsibles;
        this.createdAt = Objects.requireNonNull(createdAt, "'createdAt' should not be null");
        this.updatedAt = Objects.requireNonNull(updatedAt, "'updatedAt' should not be null");
    }

    public static Restaurant newRestaurant(
            final String name,
            final BigDecimal freightRate,
            final KitchenID kitchen,
            final Address address,
            final boolean active,
            final boolean open
    ) {
        final var anId = RestaurantID.unique();
        final var now = Instant.now();
        return new Restaurant(anId, name, freightRate, kitchen, address, active, open, new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), now, now);
    }

    public static Restaurant with(
            final RestaurantID restaurantID,
            final String name,
            final BigDecimal freightRate,
            final KitchenID kitchen,
            final Address address,
            final boolean active,
            final boolean open,
            final List<PaymentID> payments,
            final List<ProductID> products,
            final List<UserID> responsibles,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Restaurant(restaurantID, name, freightRate, kitchen, address, active, open, payments, products, responsibles, createdAt, updatedAt);
    }

    public static Restaurant with(final Restaurant aRestaurant) {
        return with(
                aRestaurant.getId(),
                aRestaurant.name,
                aRestaurant.freightRate,
                aRestaurant.kitchen,
                aRestaurant.address,
                aRestaurant.active,
                aRestaurant.open,
                aRestaurant.payments,
                aRestaurant.products,
                aRestaurant.responsibles,
                aRestaurant.createdAt,
                aRestaurant.updatedAt
        );
    }

    @Override
    public void validate(final ValidationHandler handler) {
        new RestaurantValidator(this, handler).validate();
    }

    public Restaurant update(
            final String name,
            final BigDecimal freightRate,
            final KitchenID kitchen,
            final Address address,
            final boolean active,
            final boolean open,
            final List<PaymentID> payments,
            final List<ProductID> products,
            final List<UserID> responsibles
    ) {
        if (active) {
            activate();
        } else {
            deactivate();
        }
        this.name = name;
        this.kitchen = kitchen;
        this.address = address;
        this.active = active;
        this.open = open;
        this.payments = new ArrayList<>(payments != null ? payments : Collections.emptyList());
        this.products = new ArrayList<>(products != null ? products : Collections.emptyList());
        this.responsibles = new ArrayList<>(responsibles != null ? responsibles : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant deactivate() {
        this.active = false;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant activate() {
        this.active = true;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant addPayment(final PaymentID paymentID) {
        if (paymentID == null) {
            return this;
        }
        this.payments.add(paymentID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant addPayments(final List<PaymentID> payments) {
        if (payments == null || payments.isEmpty()) {
            return this;
        }
        this.payments.addAll(payments);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant addProduct(final ProductID productID) {
        if (productID == null) {
            return this;
        }
        this.products.add(productID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant addProducts(final List<ProductID> products) {
        if (products == null || products.isEmpty()) {
            return this;
        }
        this.products.addAll(products);
        this.updatedAt = Instant.now();
        return this;
    }

    public Restaurant addResponsible(final UserID userID) {
        if (userID == null) {
            return this;
        }
        this.responsibles.add(userID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Restaurant addResponsibles(final List<UserID> users) {
        if (users == null || users.isEmpty()) {
            return this;
        }
        this.responsibles.addAll(users);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getFreightRate() {
        return freightRate;
    }

    public KitchenID getKitchen() {
        return kitchen;
    }

    public Address getAddress() {
        return address;
    }

    public boolean isActive() {
        return active;
    }

    public boolean isOpen() {
        return open;
    }

    public List<PaymentID> getPayments() {
        return Collections.unmodifiableList(payments);
    }

    public List<ProductID> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public List<UserID> getResponsibles() {
        return Collections.unmodifiableList(responsibles);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
