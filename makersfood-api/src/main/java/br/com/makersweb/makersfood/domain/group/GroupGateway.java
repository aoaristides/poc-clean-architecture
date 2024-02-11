package br.com.makersweb.makersfood.domain.group;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface GroupGateway {

    Group create(final Group group);

    void deleteById(final GroupID anId);

    Optional<Group> findById(final GroupID anId);

    Group update(final Group group);

    Pagination<Group> findAll(final SearchQuery aQuery);

    List<GroupID> existsByIds(final Iterable<GroupID> ids);

}
