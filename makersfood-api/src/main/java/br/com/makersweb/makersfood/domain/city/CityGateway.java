package br.com.makersweb.makersfood.domain.city;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface CityGateway {

    City create(final City city);

    void deleteById(final CityID anId);

    Optional<City> findById(final CityID anId);

    City update(final City city);

    Pagination<City> findAll(final SearchQuery aQuery);

    List<CityID> existsByIds(final Iterable<CityID> ids);

}
