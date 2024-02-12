package br.com.makersweb.makersfood.application.state.delete;

import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.state.StateID;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultDeleteStateUseCase extends DeleteStateUseCase {

    private final StateGateway stateGateway;

    public DefaultDeleteStateUseCase(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public void execute(final String anId) {
        this.stateGateway.deleteById(StateID.from(anId));
    }
}
