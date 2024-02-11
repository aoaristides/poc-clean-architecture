package br.com.makersweb.makersfood.domain.product;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

/**
 * @author aaristides
 */
public class ProductTest {

    @Test
    public void givenAValidParams_whenCallNewProduct_thenInstantiateACity() {
        final var expectedName = "IPhone 15 Pro Max";
        final var expectedDescription = "IPhone 15 Pro Max de 512 GB";
        final var expectedPrice = new BigDecimal(100);
        final var expectedActive = true;

        final var product = Product.newProduct(expectedName, expectedDescription, expectedPrice, expectedActive);

        Assertions.assertNotNull(product);
        Assertions.assertNotNull(product.getId());
        Assertions.assertEquals(expectedName, product.getName());
        Assertions.assertEquals(expectedDescription, product.getDescription());
        Assertions.assertEquals(expectedPrice, product.getPrice());
        Assertions.assertNull(product.getDeletedAt());
        Assertions.assertNotNull(product.getCreatedAt());
        Assertions.assertNotNull(product.getUpdatedAt());
    }
}
