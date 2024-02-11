package br.com.makersweb.makersfood.domain.restaurant;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface RestaurantGateway {

    Restaurant create(final Restaurant item);

    void deleteById(final RestaurantID anId);

    Optional<Restaurant> findById(final RestaurantID anId);

    Restaurant update(final Restaurant item);

    Pagination<Restaurant> findAll(final SearchQuery aQuery);

    List<RestaurantID> existsByIds(final Iterable<RestaurantID> ids);

}
