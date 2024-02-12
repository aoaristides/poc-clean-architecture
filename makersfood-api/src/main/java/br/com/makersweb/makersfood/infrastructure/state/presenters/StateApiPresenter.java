package br.com.makersweb.makersfood.infrastructure.state.presenters;

import br.com.makersweb.makersfood.application.state.retrieve.get.StateOutput;
import br.com.makersweb.makersfood.application.state.retrieve.list.StateListOutput;
import br.com.makersweb.makersfood.infrastructure.state.models.StateListResponse;
import br.com.makersweb.makersfood.infrastructure.state.models.StateResponse;

/**
 * @author aaristides
 */
public interface StateApiPresenter {

    static StateResponse present(final StateOutput output) {
        return new StateResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.createdAt(),
                output.updatedAt()
        );
    }

    static StateListResponse present(final StateListOutput output) {
        return new StateListResponse(
                output.id().getValue(),
                output.name(),
                output.description(),
                output.createdAt(),
                output.updatedAt()
        );
    }

}
