package br.com.makersweb.makersfood.domain.payment;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface PaymentGateway {

    Payment create(final Payment item);

    void deleteById(final PaymentID anId);

    Optional<Payment> findById(final PaymentID anId);

    Payment update(final Payment item);

    Pagination<Payment> findAll(final SearchQuery aQuery);

    List<PaymentID> existsByIds(final Iterable<PaymentID> ids);

}
