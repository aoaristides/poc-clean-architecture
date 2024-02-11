package br.com.makersweb.makersfood.domain.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class UserTest {

    @Test
    public void givenAValidParams_whenCallNewUser_thenInstantiateACity() {
        final var expectedName = "Anderson O. Aristides";
        final var expectedMail = "contato@makerswb.com.br";
        final var expectedPassword = "Salmos89!";
        final var expectedActive = true;

        final var user = User.newUser(expectedName, expectedMail, expectedPassword, expectedActive);

        Assertions.assertNotNull(user);
        Assertions.assertNotNull(user.getId());
        Assertions.assertEquals(expectedName, user.getName());
        Assertions.assertEquals(expectedMail, user.getMail());
        Assertions.assertEquals(expectedPassword, user.getPassword());
        Assertions.assertNull(user.getDeletedAt());
        Assertions.assertNotNull(user.getCreatedAt());
        Assertions.assertNotNull(user.getUpdatedAt());
    }

}
