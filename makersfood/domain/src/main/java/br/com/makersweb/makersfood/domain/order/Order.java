package br.com.makersweb.makersfood.domain.order;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.item.OrderItemID;
import br.com.makersweb.makersfood.domain.payment.PaymentID;
import br.com.makersweb.makersfood.domain.restaurant.Address;
import br.com.makersweb.makersfood.domain.restaurant.RestaurantID;
import br.com.makersweb.makersfood.domain.user.UserID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author aaristides
 */
public class Order extends AggregateRoot<OrderID> {

    private String code;
    private BigDecimal subTotal;
    private BigDecimal freightRate;
    private BigDecimal amount;
    private Address deliveryAddress;
    private Status status;
    private Instant confirmedAt;
    private Instant canceledAt;
    private Instant deliveredAt;
    private PaymentID payment;
    private RestaurantID restaurant;
    private UserID client;
    private List<OrderItemID> items;
    private Instant createdAt;
    private Instant updatedAt;

    private Order(
            final OrderID orderID,
            final String code,
            final BigDecimal subTotal,
            final BigDecimal freightRate,
            final BigDecimal amount,
            final Address deliveryAddress,
            final Status status,
            final Instant confirmedAt,
            final Instant canceledAt,
            final Instant deliveredAt,
            final PaymentID payment,
            final RestaurantID restaurant,
            final UserID client,
            final List<OrderItemID> items,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(orderID);
        this.code = code;
        this.subTotal = subTotal;
        this.freightRate = freightRate;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.confirmedAt = confirmedAt;
        this.canceledAt = canceledAt;
        this.deliveredAt = deliveredAt;
        this.payment = payment;
        this.restaurant = restaurant;
        this.client = client;
        this.items = items;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static Order newOrder(
            final String code,
            final BigDecimal subTotal,
            final BigDecimal freightRate,
            final BigDecimal amount
    ) {
        final var anId = OrderID.unique();
        final var now = InstantUtils.now();
        final var status = Status.CREATED;
        return new Order(
                anId,
                code,
                subTotal,
                freightRate,
                amount,
                null,
                status,
                null,
                null,
                null,
                null,
                null,
                null,
                new ArrayList<>(),
                now,
                now
        );
    }

    public static Order with(
            final OrderID orderID,
            final String code,
            final BigDecimal subTotal,
            final BigDecimal freightRate,
            final BigDecimal amount,
            final Address deliveryAddress,
            final Status status,
            final Instant confirmedAt,
            final Instant canceledAt,
            final Instant deliveredAt,
            final PaymentID payment,
            final RestaurantID restaurant,
            final UserID client,
            final List<OrderItemID> items,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new Order(
                orderID,
                code,
                subTotal,
                freightRate,
                amount,
                deliveryAddress,
                status,
                confirmedAt,
                canceledAt,
                deliveredAt,
                payment,
                restaurant,
                client,
                new ArrayList<>(items),
                createdAt,
                updatedAt
        );
    }

    public static Order with(final Order aOrder) {
        return with(
                aOrder.getId(),
                aOrder.code,
                aOrder.subTotal,
                aOrder.freightRate,
                aOrder.amount,
                aOrder.deliveryAddress,
                aOrder.status,
                aOrder.confirmedAt,
                aOrder.canceledAt,
                aOrder.deliveredAt,
                aOrder.payment,
                aOrder.restaurant,
                aOrder.client,
                aOrder.items,
                aOrder.createdAt,
                aOrder.updatedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderValidator(this, handler).validate();
    }

    public Order update(
            final String code,
            final BigDecimal subTotal,
            final BigDecimal freightRate,
            final BigDecimal amount,
            final Address deliveryAddress,
            final Status status,
            final Instant confirmedAt,
            final Instant canceledAt,
            final Instant deliveredAt,
            final PaymentID payment,
            final RestaurantID restaurant,
            final UserID client,
            final List<OrderItemID> items
    ) {
        this.code = code;
        this.subTotal = subTotal;
        this.freightRate = freightRate;
        this.amount = amount;
        this.deliveryAddress = deliveryAddress;
        this.status = status;
        this.confirmedAt = confirmedAt;
        this.canceledAt = canceledAt;
        this.deliveredAt = deliveredAt;
        this.payment = payment;
        this.restaurant = restaurant;
        this.client = client;
        this.items = new ArrayList<>(items != null ? items : Collections.emptyList());
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addDeliveryAddress(final Address deliveryAddress) {
        if (deliveryAddress == null) {
            return this;
        }
        this.deliveryAddress = deliveryAddress;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addPayment(final PaymentID paymentID) {
        if (paymentID == null) {
            return this;
        }
        this.payment = paymentID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addClient(final UserID userID) {
        if (userID == null) {
            return this;
        }
        this.client = userID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addRestaurant(final RestaurantID restaurantID) {
        if (restaurantID == null) {
            return this;
        }
        this.restaurant = restaurantID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addItem(final OrderItemID itemID) {
        if (itemID == null) {
            return this;
        }
        this.items.add(itemID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order addItems(final List<OrderItemID> items) {
        if (items == null || items.isEmpty()) {
            return this;
        }
        this.items.addAll(items);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public Order removeItem(final OrderItemID itemID) {
        if (itemID == null) {
            return this;
        }
        this.items.remove(itemID);
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public BigDecimal getFreightRate() {
        return freightRate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Address getDeliveryAddress() {
        return deliveryAddress;
    }

    public Status getStatus() {
        return status;
    }

    public Instant getConfirmedAt() {
        return confirmedAt;
    }

    public Instant getCanceledAt() {
        return canceledAt;
    }

    public Instant getDeliveredAt() {
        return deliveredAt;
    }

    public PaymentID getPayment() {
        return payment;
    }

    public RestaurantID getRestaurant() {
        return restaurant;
    }

    public UserID getClient() {
        return client;
    }

    public List<OrderItemID> getItems() {
        return Collections.unmodifiableList(items);
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
