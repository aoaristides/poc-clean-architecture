package br.com.makersweb.makersfood.domain.item;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface OrderItemGateway {

    OrderItem create(final OrderItem item);

    void deleteById(final OrderItemID anId);

    Optional<OrderItem> findById(final OrderItemID anId);

    OrderItem update(final OrderItem item);

    Pagination<OrderItem> findAll(final SearchQuery aQuery);

    List<OrderItemID> existsByIds(final Iterable<OrderItemID> ids);

}
