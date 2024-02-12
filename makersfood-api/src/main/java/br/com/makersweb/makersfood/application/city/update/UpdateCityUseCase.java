package br.com.makersweb.makersfood.application.city.update;

import br.com.makersweb.makersfood.application.UseCase;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class UpdateCityUseCase extends UseCase<UpdateCityCommand, Either<Notification, UpdateCityOutput>> {
}
