package br.com.makersweb.makersfood.domain.user;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface UserGateway {

    User create(final User item);

    void deleteById(final UserID anId);

    Optional<User> findById(final UserID anId);

    User update(final User item);

    Pagination<User> findAll(final SearchQuery aQuery);

    List<UserID> existsByIds(final Iterable<UserID> ids);

}
