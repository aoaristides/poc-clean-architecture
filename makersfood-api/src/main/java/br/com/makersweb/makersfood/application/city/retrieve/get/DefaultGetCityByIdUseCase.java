package br.com.makersweb.makersfood.application.city.retrieve.get;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.city.CityID;
import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.exceptions.NotFoundException;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author aaristides
 */
public class DefaultGetCityByIdUseCase extends GetCityByIdUseCase {

    private final CityGateway cityGateway;

    public DefaultGetCityByIdUseCase(final CityGateway cityGateway) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
    }

    @Override
    public CityOutput execute(final String anId) {
        final var aCityID = CityID.from(anId);
        return this.cityGateway.findById(aCityID).map(CityOutput::from).orElseThrow(notFound(aCityID));
    }

    private Supplier<DomainException> notFound(final CityID anId) {
        return () -> NotFoundException.with(City.class, anId);
    }
}
