package br.com.makersweb.makersfood.infrastructure.api.controllers;

import br.com.makersweb.makersfood.application.city.create.CreateCityCommand;
import br.com.makersweb.makersfood.application.city.create.CreateCityOutput;
import br.com.makersweb.makersfood.application.city.create.CreateCityUseCase;
import br.com.makersweb.makersfood.application.city.delete.DeleteCityUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.get.GetCityByIdUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.list.ListCitiesUseCase;
import br.com.makersweb.makersfood.application.city.update.UpdateCityCommand;
import br.com.makersweb.makersfood.application.city.update.UpdateCityOutput;
import br.com.makersweb.makersfood.application.city.update.UpdateCityUseCase;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import br.com.makersweb.makersfood.infrastructure.api.CityAPI;
import br.com.makersweb.makersfood.infrastructure.city.models.CityListResponse;
import br.com.makersweb.makersfood.infrastructure.city.models.CityResponse;
import br.com.makersweb.makersfood.infrastructure.city.models.CreateCityRequest;
import br.com.makersweb.makersfood.infrastructure.city.models.UpdateCityRequest;
import br.com.makersweb.makersfood.infrastructure.city.presenters.CityApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author aaristides
 */
@RestController
public class CityController implements CityAPI {

    private final CreateCityUseCase createCityUseCase;
    private final UpdateCityUseCase updateCityUseCase;
    private final GetCityByIdUseCase getCityByIdUseCase;
    private final ListCitiesUseCase listCitiesUseCase;
    private final DeleteCityUseCase deleteCityUseCase;

    public CityController(
            final CreateCityUseCase createCityUseCase,
            final UpdateCityUseCase updateCityUseCase,
            final GetCityByIdUseCase getCityByIdUseCase,
            final ListCitiesUseCase listCitiesUseCase,
            final DeleteCityUseCase deleteCityUseCase
    ) {
        this.createCityUseCase = Objects.requireNonNull(createCityUseCase);
        this.updateCityUseCase = Objects.requireNonNull(updateCityUseCase);
        this.getCityByIdUseCase = Objects.requireNonNull(getCityByIdUseCase);
        this.listCitiesUseCase = Objects.requireNonNull(listCitiesUseCase);
        this.deleteCityUseCase = Objects.requireNonNull(deleteCityUseCase);
    }

    @Override
    public ResponseEntity<?> createCity(final CreateCityRequest input) {
        final var aCommand = CreateCityCommand.with(input.name(), input.state());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<CreateCityOutput, ResponseEntity<?>> onSucces = output ->
                ResponseEntity.created(URI.create("/cities/" + output.id())).body(output);

        return this.createCityUseCase.execute(aCommand).fold(onError, onSucces);
    }

    @Override
    public Pagination<CityListResponse> listCities(
            final String search,
            final int page,
            final int perPage,
            final String sort,
            final String direction
    ) {
        return this.listCitiesUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(CityApiPresenter::present);
    }

    @Override
    public CityResponse getById(final String id) {
        return CityApiPresenter.present(this.getCityByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateCityRequest input) {
        final var aCommand = UpdateCityCommand.with(id, input.name(), input.state());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateCityOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateCityUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteCityUseCase.execute(id);
    }
}
