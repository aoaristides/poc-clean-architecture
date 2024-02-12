package br.com.makersweb.makersfood.domain.state;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface StateGateway {

    State create(final State item);

    void deleteById(final StateID anId);

    Optional<State> findById(final StateID anId);

    Optional<State> findByName(final String name);

    State update(final State item);

    Pagination<State> findAll(final SearchQuery aQuery);

    List<StateID> existsByIds(final Iterable<StateID> ids);

}
