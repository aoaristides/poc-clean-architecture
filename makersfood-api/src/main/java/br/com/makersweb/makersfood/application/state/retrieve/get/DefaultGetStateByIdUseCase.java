package br.com.makersweb.makersfood.application.state.retrieve.get;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.exceptions.NotFoundException;
import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.state.StateID;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author aaristides
 */
public class DefaultGetStateByIdUseCase extends GetStateByIdUseCase {

    private final StateGateway stateGateway;

    public DefaultGetStateByIdUseCase(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public StateOutput execute(final String anId) {
        final var aStateID = StateID.from(anId);
        return this.stateGateway.findById(aStateID).map(StateOutput::from).orElseThrow(notFound(aStateID));
    }

    private Supplier<DomainException> notFound(final StateID anId) {
        return () -> NotFoundException.with(State.class, anId);
    }
}
