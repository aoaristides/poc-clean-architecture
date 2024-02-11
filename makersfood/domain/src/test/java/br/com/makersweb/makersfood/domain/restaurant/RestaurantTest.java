package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.address.Address;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class RestaurantTest {

    @Test
    public void givenAValidParams_whenCallNewRestaurant_thenInstantiateACity() {
        final var expectedName = "IPhone 15 Pro Max";
        final var expectedFreightRate = new BigDecimal(10);
        final var expectedAddress = Address.newAddress("86480000", "Rua Domingos Ferreira de Quadros", "412", "Casa", "Centro");
        final var expectedActive = true;
        final var expectedOpen = true;

        final var product = Restaurant.newRestaurant(expectedName, expectedFreightRate, expectedAddress, expectedActive, expectedOpen);

        Assertions.assertNotNull(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(expectedName, product.getName());
        Assertions.assertEquals(expectedFreightRate, product.getFreightRate());
        Assertions.assertEquals(expectedAddress, product.getAddress());
        Assertions.assertEquals(expectedActive, product.isActive());
        Assertions.assertEquals(expectedOpen, product.isOpen());
        Assertions.assertNotNull(product.getCreatedAt());
        Assertions.assertNotNull(product.getUpdatedAt());
    }

}
