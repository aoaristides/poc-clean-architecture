package br.com.makersweb.makersfood.application.state.update;

import br.com.makersweb.makersfood.application.UseCase;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class UpdateStateUseCase extends UseCase<UpdateStateCommand, Either<Notification, UpdateStateOutput>> {
}
