package br.com.makersweb.makersfood.domain.item;

import br.com.makersweb.makersfood.domain.AggregateRoot;
import br.com.makersweb.makersfood.domain.order.OrderID;
import br.com.makersweb.makersfood.domain.product.ProductID;
import br.com.makersweb.makersfood.domain.utils.InstantUtils;
import br.com.makersweb.makersfood.domain.validation.ValidationHandler;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author aaristides
 */
public class OrderItem extends AggregateRoot<OrderItemID> {

    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private Integer quantity;
    private String observation;
    private OrderID order;
    private ProductID product;
    private Instant createdAt;
    private Instant updatedAt;

    private OrderItem(
            final OrderItemID itemID,
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Integer quantity,
            final String observation,
            final OrderID order,
            final ProductID product,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        super(itemID);
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.observation = observation;
        this.order = order;
        this.product = product;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static OrderItem newOrderItem(
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Integer quantity,
            final String observation
    ) {
        final var anId = OrderItemID.unique();
        final var now = InstantUtils.now();
        return new OrderItem(anId, unitPrice, totalPrice, quantity, observation, null, null, now, now);
    }

    public static OrderItem with(
            final OrderItemID itemID,
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Integer quantity,
            final String observation,
            final OrderID order,
            final ProductID product,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        return new OrderItem(itemID, unitPrice, totalPrice, quantity, observation, order, product, createdAt, updatedAt);
    }

    public static OrderItem with(final OrderItem aItem) {
        return with(
                aItem.getId(),
                aItem.unitPrice,
                aItem.totalPrice,
                aItem.quantity,
                aItem.observation,
                aItem.order,
                aItem.product,
                aItem.createdAt,
                aItem.updatedAt
        );
    }

    @Override
    public void validate(ValidationHandler handler) {
        new OrderItemValidator(this, handler).validate();
    }

    public OrderItem update(
            final BigDecimal unitPrice,
            final BigDecimal totalPrice,
            final Integer quantity,
            final String observation,
            final OrderID order,
            final ProductID product
    ) {
        this.unitPrice = unitPrice;
        this.totalPrice = totalPrice;
        this.quantity = quantity;
        this.observation = observation;
        this.order = order;
        this.product = product;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public OrderItem addOrder(final OrderID orderID) {
        if (orderID == null) {
            return this;
        }
        this.order = orderID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public OrderItem addProduct(final ProductID productID) {
        if (productID == null) {
            return this;
        }
        this.product = productID;
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public OrderItem calculatePriceTotal() {
        var unitPrice = this.getUnitPrice();
        var quantity = this.getQuantity();
        if (unitPrice == null) {
            unitPrice = BigDecimal.ZERO;
        }
        if (quantity == null) {
            quantity = 0;
        }
        this.totalPrice = unitPrice.multiply(new BigDecimal(quantity));
        this.updatedAt = InstantUtils.now();
        return this;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getObservation() {
        return observation;
    }

    public OrderID getOrder() {
        return order;
    }

    public ProductID getProduct() {
        return product;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
}
