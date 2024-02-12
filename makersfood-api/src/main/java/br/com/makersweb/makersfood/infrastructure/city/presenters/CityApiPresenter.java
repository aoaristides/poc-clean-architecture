package br.com.makersweb.makersfood.infrastructure.city.presenters;

import br.com.makersweb.makersfood.application.city.retrieve.get.CityOutput;
import br.com.makersweb.makersfood.application.city.retrieve.list.CityListOutput;
import br.com.makersweb.makersfood.infrastructure.city.models.CityListResponse;
import br.com.makersweb.makersfood.infrastructure.city.models.CityResponse;

/**
 * @author aaristides
 */
public interface CityApiPresenter {

    static CityResponse present(final CityOutput output) {
        return new CityResponse(
                output.id().getValue(),
                output.name(),
                output.state().getValue(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static CityListResponse present(final CityListOutput output) {
        return new CityListResponse(
                output.id().getValue(),
                output.name(),
                output.state().getValue(),
                output.createdAt(),
                output.updatedAt()
        );
    }

}
