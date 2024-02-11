package br.com.makersweb.makersfood.domain.group;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class GroupTest {

    @Test
    public void givenAValidParams_whenCallNewGroup_thenInstantiateACity() {
        final var expectedName = "Admin";

        final var actualGroup = Group.newGroup(expectedName);

        Assertions.assertNotNull(actualGroup);
        Assertions.assertNotNull(actualGroup.getId());
        Assertions.assertEquals(expectedName, actualGroup.getName());
        Assertions.assertNotNull(actualGroup.getCreatedAt());
        Assertions.assertNotNull(actualGroup.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewGroupAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualGroup = Group.newGroup(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualGroup.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

}
