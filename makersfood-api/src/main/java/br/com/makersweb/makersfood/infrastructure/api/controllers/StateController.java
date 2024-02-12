package br.com.makersweb.makersfood.infrastructure.api.controllers;

import br.com.makersweb.makersfood.application.state.create.CreateStateCommand;
import br.com.makersweb.makersfood.application.state.create.CreateStateUseCase;
import br.com.makersweb.makersfood.application.state.delete.DeleteStateUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.get.GetStateByIdUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.list.ListStateUseCase;
import br.com.makersweb.makersfood.application.state.update.UpdateStateCommand;
import br.com.makersweb.makersfood.application.state.update.UpdateStateOutput;
import br.com.makersweb.makersfood.application.state.update.UpdateStateUseCase;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;
import br.com.makersweb.makersfood.domain.validation.handler.Notification;
import br.com.makersweb.makersfood.infrastructure.api.StateAPI;
import br.com.makersweb.makersfood.infrastructure.state.models.CreateStateRequest;
import br.com.makersweb.makersfood.infrastructure.state.models.StateListResponse;
import br.com.makersweb.makersfood.infrastructure.state.models.StateResponse;
import br.com.makersweb.makersfood.infrastructure.state.models.UpdateStateRequest;
import br.com.makersweb.makersfood.infrastructure.state.presenters.StateApiPresenter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;
import java.util.function.Function;

/**
 * @author aaristides
 */
@RestController
public class StateController implements StateAPI {

    private final CreateStateUseCase createStateUseCase;
    private final UpdateStateUseCase updateStateUseCase;
    private final GetStateByIdUseCase getStateByIdUseCase;
    private final ListStateUseCase listStateUseCase;
    private final DeleteStateUseCase deleteStateUseCase;

    public StateController(
            final CreateStateUseCase createStateUseCase,
            final UpdateStateUseCase updateStateUseCase,
            final GetStateByIdUseCase getStateByIdUseCase,
            final ListStateUseCase listStateUseCase,
            final DeleteStateUseCase deleteStateUseCase
    ) {
        this.createStateUseCase = Objects.requireNonNull(createStateUseCase);
        this.updateStateUseCase = Objects.requireNonNull(updateStateUseCase);
        this.getStateByIdUseCase = Objects.requireNonNull(getStateByIdUseCase);
        this.listStateUseCase = Objects.requireNonNull(listStateUseCase);
        this.deleteStateUseCase = Objects.requireNonNull(deleteStateUseCase);
    }

    @Override
    public ResponseEntity<?> createState(final CreateStateRequest input) {
        final var aCommand = CreateStateCommand.with(input.name(), input.description());

        final var output = this.createStateUseCase.execute(aCommand);

        return ResponseEntity.created(URI.create("/states/" + output.id())).body(output);
    }

    @Override
    public Pagination<StateListResponse> listStates(final String search, final int page, final int perPage, final String sort, final String direction) {
        return this.listStateUseCase.execute(new SearchQuery(page, perPage, search, sort, direction))
                .map(StateApiPresenter::present);
    }

    @Override
    public StateResponse getById(final String id) {
        return StateApiPresenter.present(this.getStateByIdUseCase.execute(id));
    }

    @Override
    public ResponseEntity<?> updateById(final String id, final UpdateStateRequest input) {
        final var aCommand = UpdateStateCommand.with(id, input.name(), input.description());

        final Function<Notification, ResponseEntity<?>> onError = notification ->
                ResponseEntity.unprocessableEntity().body(notification);

        final Function<UpdateStateOutput, ResponseEntity<?>> onSuccess = ResponseEntity::ok;

        return this.updateStateUseCase.execute(aCommand).fold(onError, onSuccess);
    }

    @Override
    public void deleteById(final String id) {
        this.deleteStateUseCase.execute(id);
    }

}
