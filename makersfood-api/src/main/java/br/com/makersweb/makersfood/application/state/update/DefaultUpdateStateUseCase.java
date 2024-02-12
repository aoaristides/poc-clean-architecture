package br.com.makersweb.makersfood.application.state.update;

import br.com.makersweb.makersfood.domain.exceptions.DomainException;
import br.com.makersweb.makersfood.domain.exceptions.NotFoundException;
import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.state.StateID;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

import java.util.Objects;
import java.util.function.Supplier;

import static io.vavr.API.Left;
import static io.vavr.API.Try;

/**
 * @author aaristides
 */
public class DefaultUpdateStateUseCase extends UpdateStateUseCase {

    private final StateGateway stateGateway;

    public DefaultUpdateStateUseCase(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Override
    public Either<Notification, UpdateStateOutput> execute(final UpdateStateCommand aCommand) {
        final var anId = StateID.from(aCommand.id());
        final var aName = aCommand.name();

        final var aState = this.stateGateway.findById(anId).orElseThrow(notFound(anId));
        final var notification = Notification.create();
        aState.update(aName).validate(notification);

        return notification.hasError() ? Left(notification) : update(aState);
    }

    private Either<Notification, UpdateStateOutput> update(final State aState) {
        return Try(() -> this.stateGateway.update(aState))
                .toEither()
                .bimap(Notification::create, UpdateStateOutput::from);
    }

    private Supplier<DomainException> notFound(final StateID anId) {
        return () -> NotFoundException.with(State.class, anId);
    }

}
