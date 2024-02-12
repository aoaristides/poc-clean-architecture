package br.com.makersweb.makersfood.application.city.retrieve.list;

import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultListCitiesUseCase extends ListCitiesUseCase {

    private final CityGateway cityGateway;

    public DefaultListCitiesUseCase(final CityGateway cityGateway) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
    }

    @Override
    public Pagination<CityListOutput> execute(final SearchQuery aQuery) {
        return this.cityGateway.findAll(aQuery).map(CityListOutput::from);
    }
}
