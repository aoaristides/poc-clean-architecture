package br.com.makersweb.makersfood.domain.product;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface ProductGateway {

    Product create(final Product item);

    void deleteById(final ProductID anId);

    Optional<Product> findById(final ProductID anId);

    Product update(final Product item);

    Pagination<Product> findAll(final SearchQuery aQuery);

    List<ProductID> existsByIds(final Iterable<ProductID> ids);

}
