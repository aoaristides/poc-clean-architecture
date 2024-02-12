package br.com.makersweb.makersfood.application.state.retrieve.list;

import br.com.makersweb.makersfood.application.UseCase;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

/**
 * @author aaristides
 */
public abstract class ListStateUseCase extends UseCase<SearchQuery, Pagination<StateListOutput>> {
}
