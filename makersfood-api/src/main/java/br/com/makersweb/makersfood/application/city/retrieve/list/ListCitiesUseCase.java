package br.com.makersweb.makersfood.application.city.retrieve.list;

import br.com.makersweb.makersfood.application.UseCase;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;

/**
 * @author aaristides
 */
public abstract class ListCitiesUseCase extends UseCase<SearchQuery, Pagination<CityListOutput>> {
}
