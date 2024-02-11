package br.com.makersweb.makersfood.domain.permission;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface PermissionGateway {

    Permission create(final Permission item);

    void deleteById(final PermissionID anId);

    Optional<Permission> findById(final PermissionID anId);

    Permission update(final Permission item);

    Pagination<Permission> findAll(final SearchQuery aQuery);

    List<PermissionID> existsByIds(final Iterable<PermissionID> ids);

}
