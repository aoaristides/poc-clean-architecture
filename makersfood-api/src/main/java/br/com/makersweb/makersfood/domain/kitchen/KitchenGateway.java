package br.com.makersweb.makersfood.domain.kitchen;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface KitchenGateway {

    Kitchen create(final Kitchen item);

    void deleteById(final KitchenID anId);

    Optional<Kitchen> findById(final KitchenID anId);

    Kitchen update(final Kitchen item);

    Pagination<Kitchen> findAll(final SearchQuery aQuery);

    List<KitchenID> existsByIds(final Iterable<KitchenID> ids);

}
