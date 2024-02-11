package br.com.makersweb.makersfood.application.city.create;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.state.StateID;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultCreateCityUseCase extends CreateCityUseCase {

    private final CityGateway cityGateway;

    public DefaultCreateCityUseCase(final CityGateway cityGateway) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
    }

    @Override
    public CreateCityOutput execute(final CreateCityCommand aCommand) {
        final var aName = aCommand.name();
        final var aState = StateID.from(aCommand.state());

        final var notification = Notification.create();
        final var aCity = notification.validate(() -> City.newCity(aName));
        aCity.addState(aState);

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate City", notification);
        }

        return CreateCityOutput.from(this.cityGateway.create(aCity));
    }
}
