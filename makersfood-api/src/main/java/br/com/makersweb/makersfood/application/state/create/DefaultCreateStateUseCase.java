package br.com.makersweb.makersfood.application.state.create;

import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;

import java.util.Objects;

/**
 * @author aaristides
 */
public class DefaultCreateStateUseCase extends CreateStateUseCase {

    private final StateGateway stateGateway;

    public DefaultCreateStateUseCase(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public CreateStateOutput execute(final CreateStateCommand aCommand) {
        final var aName = aCommand.name();
        final var notification = Notification.create();

        final var aState = notification.validate(() -> State.newState(aName));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate State", notification);
        }

        return CreateStateOutput.from(this.stateGateway.create(aState));
    }
}
