package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class StateTest {

    @Test
    public void givenAValidParams_whenCallNewState_thenInstantiateAState() {
        final var expectedName = "PR";

        final var actualState = State.newState(expectedName);

        Assertions.assertNotNull(actualState);
        Assertions.assertNotNull(actualState.getId());
        Assertions.assertEquals(expectedName, actualState.getName());
        Assertions.assertNotNull(actualState.getCreatedAt());
        Assertions.assertNotNull(actualState.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualState = State.newState(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualState = State.newState(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan2_whenCallNewStateAndValidate_thenShouldReceiveError() {
        final var expectedName = "F ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must have 2 characters";

        final var actualState = State.newState(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMore2_whenCallNewCityAndValidate_thenShouldReceiveError() {
        final var expectedName = """
                A certificação de metodologias que nos auxiliam a lidar com a execução dos pontos do programa garante a contribuição de um grupo importante na determinação dos modos de operação convencionais. 
                O empenho em analisar o desenvolvimento contínuo de distintas formas de atuação maximiza as possibilidades por conta das direções preferenciais no sentido do progresso.
                Desta maneira, o acompanhamento das preferências de consumo causa impacto indireto na reavaliação do sistema de formação de quadros que corresponde às necessidades.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must have 2 characters";

        final var actualState = State.newState(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualState.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

}
