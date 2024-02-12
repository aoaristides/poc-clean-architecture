package br.com.makersweb.makersfood.application.state.retrieve.list;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;
import br.com.makersweb.makersfood.domain.state.StateGateway;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultListStateUseCase extends ListStateUseCase {

    private final StateGateway stateGateway;

    public DefaultListStateUseCase(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public Pagination<StateListOutput> execute(final SearchQuery aQuery) {
        return this.stateGateway.findAll(aQuery).map(StateListOutput::from);
    }
}
