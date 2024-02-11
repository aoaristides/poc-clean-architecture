package br.com.makersweb.makersfood.domain.order;

import br.com.makersweb.makersfood.domain.address.Address;
import br.com.makersweb.makersfood.domain.item.OrderItemID;
import br.com.makersweb.makersfood.domain.payment.PaymentID;
import br.com.makersweb.makersfood.domain.restaurant.RestaurantID;
import br.com.makersweb.makersfood.domain.user.UserID;
import br.com.makersweb.makersfood.domain.utils.IdUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class OrderTest {

    @Test
    public void givenAValidParams_whenCallNewOrder_thenInstantiateACity() {
        final var expectedCode = "0000000001";
        final var expectedSubTotal = new BigDecimal(100);
        final var expectedFreightRate = new BigDecimal(10);
        final var expectedAmount = new BigDecimal(110);
        final var expectedClient = UserID.from(IdUtils.uuid());
        final var expectedPayment = PaymentID.from(IdUtils.uuid());
        final var expectedRestaurant = RestaurantID.from(IdUtils.uuid());
        final var expectedDeliveryAddress = Address.newAddress("86480000", "Rua Domingos Ferreira de Quadros", "412", "Casa", "Centro");
        final var expectedOrderItem = OrderItemID.from(IdUtils.uuid());

        final var order = Order.newOrder(expectedCode, expectedSubTotal, expectedFreightRate, expectedAmount);
        order.addClient(expectedClient);
        order.addPayment(expectedPayment);
        order.addRestaurant(expectedRestaurant);
        order.addDeliveryAddress(expectedDeliveryAddress);
        order.addItem(expectedOrderItem);

        Assertions.assertNotNull(order);
        Assertions.assertNotNull(order.getId());
        Assertions.assertEquals(expectedCode, order.getCode());
        Assertions.assertEquals(expectedFreightRate, order.getFreightRate());
        Assertions.assertEquals(expectedSubTotal, order.getSubTotal());
        Assertions.assertEquals(expectedAmount, order.getAmount());
        Assertions.assertEquals(expectedClient, order.getClient());
        Assertions.assertEquals(expectedPayment, order.getPayment());
        Assertions.assertEquals(expectedRestaurant, order.getRestaurant());
        Assertions.assertEquals(expectedDeliveryAddress, order.getDeliveryAddress());
        Assertions.assertTrue(order.getItems().contains(expectedOrderItem));
        Assertions.assertNotNull(order.getCreatedAt());
        Assertions.assertNotNull(order.getUpdatedAt());
    }

}
