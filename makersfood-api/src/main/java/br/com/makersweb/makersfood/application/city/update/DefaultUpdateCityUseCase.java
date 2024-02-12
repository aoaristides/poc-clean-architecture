package br.com.makersweb.makersfood.application.city.update;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.city.CityID;
import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.exceptions.NotFoundException;
import br.com.makersweb.makersfood.domain.state.StateID;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultUpdateCityUseCase extends UpdateCityUseCase {

    private final CityGateway cityGateway;

    public DefaultUpdateCityUseCase(final CityGateway cityGateway) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
    }

    @Override
    public Either<Notification, UpdateCityOutput> execute(final UpdateCityCommand aCommand) {
        final var anId = CityID.from(aCommand.id());
        final var aName = aCommand.name();
        final var aStateId = StateID.from(aCommand.state());

        final var aCity = this.cityGateway.findById(anId).orElseThrow(notFound(anId));
        final var notification = Notification.create();
        aCity.update(aName, aStateId).validate(notification);

        return notification.hasError() ? Left(notification) : update(aCity);
    }

    private Either<Notification, UpdateCityOutput> update(final City aCity) {
        return Try(() -> this.cityGateway.update(aCity))
                .toEither()
                .bimap(Notification::create, UpdateCityOutput::from);
    }

    private Supplier<DomainException> notFound(final CityID anId) {
        return () -> NotFoundException.with(City.class, anId);
    }

}
