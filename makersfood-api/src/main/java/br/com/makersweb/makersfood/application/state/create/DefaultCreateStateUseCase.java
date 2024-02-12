package br.com.makersweb.makersfood.application.state.create;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.exceptions.NotificationException;
import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.validation.Error;
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
        final var aDescription = aCommand.description();
        final var notification = Notification.create();

        if (this.stateGateway.findByName(aName).isPresent()) {
            notification.append(new Error("A state with that name %s already exists.".formatted(aName)));
        }

        final var aState = notification.validate(() -> State.newState(aName, aDescription));

        if (notification.hasError()) {
            throw new NotificationException("Could not create Aggregate State", notification);
        }

        return CreateStateOutput.from(this.stateGateway.create(aState));
    }
}
