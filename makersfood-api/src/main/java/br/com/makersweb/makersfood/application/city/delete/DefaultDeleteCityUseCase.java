package br.com.makersweb.makersfood.application.city.delete;

import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.city.CityID;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultDeleteCityUseCase extends DeleteCityUseCase {

    private final CityGateway cityGateway;

    public DefaultDeleteCityUseCase(final CityGateway cityGateway) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
    }

    @Override
    public void execute(final String anId) {
        this.cityGateway.deleteById(CityID.from(anId));
    }
}
