package br.com.makersweb.makersfood.application.city.create;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.validation.Error;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultCreateCityUseCase extends CreateCityUseCase {

    private final CityGateway cityGateway;
    private final StateGateway stateGateway;

    public DefaultCreateCityUseCase(
            final CityGateway cityGateway,
            final StateGateway stateGateway
    ) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public Either<Notification, CreateCityOutput> execute(final CreateCityCommand aCommand) {
        final var aName = aCommand.name();
        final var aState = aCommand.state();
        final var notification = Notification.create();

        if (this.cityGateway.findByName(aName).isPresent()) {
            notification.append(new Error("A city with that name %s already exists.".formatted(aName)));
        }

        final var aCity = notification.validate(() -> City.newCity(aName));
        this.stateGateway.findByName(aState).ifPresent(state -> aCity.addState(state.getId()));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate City", notification);
        }

        return notification.hasError() ? Left(notification) : create(aCity);
    }

    private Either<Notification, CreateCityOutput> create(final City aCity) {
        return Try(() -> this.cityGateway.create(aCity))
                .toEither()
                .bimap(Notification::create, CreateCityOutput::from);
    }
}
