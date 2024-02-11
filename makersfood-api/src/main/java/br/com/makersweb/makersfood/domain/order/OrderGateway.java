package br.com.makersweb.makersfood.domain.order;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface OrderGateway {

    Order create(final Order item);

    void deleteById(final OrderID anId);

    Optional<Order> findById(final OrderID anId);

    Order update(final Order item);

    Pagination<Order> findAll(final SearchQuery aQuery);

    List<OrderID> existsByIds(final Iterable<OrderID> ids);

}
