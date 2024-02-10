package br.com.makersweb.makersfood.domain.city;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.validation.handler.ThrowsValidationHandler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author aaristides
 */
public class CityTest {

    @Test
    public void givenAValidParams_whenCallNewCity_thenInstantiateACity() {
        final var expectedName = "Conselheiro Mairinck";

        final var actualCity = City.newCity(expectedName);

        Assertions.assertNotNull(actualCity);
        Assertions.assertNotNull(actualCity.getId());
        Assertions.assertEquals(expectedName, actualCity.getName());
        Assertions.assertNotNull(actualCity.getCreatedAt());
        Assertions.assertNotNull(actualCity.getUpdatedAt());
    }

    @Test
    public void givenAnInvalidNullName_whenCallNewCityAndValidate_thenShouldReceiveError() {
        final String expectedName = null;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be null";

        final var actualCity = City.newCity(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCity.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidEmptyName_whenCallNewCityAndValidate_thenShouldReceiveError() {
        final var expectedName = " ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' should not be empty";

        final var actualCity = City.newCity(expectedName);

        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCity.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthLessThan3_whenCallNewCityAndValidate_thenShouldReceiveError() {
        final var expectedName = "Fi ";
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualCity = City.newCity(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCity.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

    @Test
    public void givenAnInvalidNameLengthMore255_whenCallNewCityAndValidate_thenShouldReceiveError() {
        final var expectedName = """
                A certificação de metodologias que nos auxiliam a lidar com a execução dos pontos do programa garante a contribuição de um grupo importante na determinação dos modos de operação convencionais. 
                O empenho em analisar o desenvolvimento contínuo de distintas formas de atuação maximiza as possibilidades por conta das direções preferenciais no sentido do progresso.
                Desta maneira, o acompanhamento das preferências de consumo causa impacto indireto na reavaliação do sistema de formação de quadros que corresponde às necessidades.
                """;
        final var expectedErrorCount = 1;
        final var expectedErrorMessage = "'name' must be between 3 and 255 characters";

        final var actualCity = City.newCity(expectedName);
        final var actualException = Assertions.assertThrows(DomainException.class, () -> actualCity.validate(new ThrowsValidationHandler()));

        Assertions.assertEquals(expectedErrorCount, actualException.getErrors().size());
        Assertions.assertEquals(expectedErrorMessage, actualException.getErrors().get(0).message());
    }

}
