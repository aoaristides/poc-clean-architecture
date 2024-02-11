package br.com.makersweb.makersfood.domain.kitchen;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class KitchenTest {

    @Test
    public void givenAValidParams_whenCallNewKitchen_thenInstantiateACity() {
        final var expectedName = "Admin";

        final var actualKitchen = Kitchen.newKitchen(expectedName);

        Assertions.assertNotNull(actualKitchen);
        Assertions.assertNotNull(actualKitchen.getId());
        Assertions.assertEquals(expectedName, actualKitchen.getName());
        Assertions.assertNotNull(actualKitchen.getCreatedAt());
        Assertions.assertNotNull(actualKitchen.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewKitchenAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualKitchen = Kitchen.newKitchen(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualKitchen.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }
}
