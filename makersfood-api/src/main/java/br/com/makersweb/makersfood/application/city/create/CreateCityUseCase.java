package br.com.makersweb.makersfood.application.city.create;

import br.com.makersweb.makersfood.application.UseCase;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import io.vavr.control.Either;

/**
 * @author aaristides
 */
public abstract class CreateCityUseCase extends UseCase<CreateCityCommand, Either<Notification, CreateCityOutput>> {
}
