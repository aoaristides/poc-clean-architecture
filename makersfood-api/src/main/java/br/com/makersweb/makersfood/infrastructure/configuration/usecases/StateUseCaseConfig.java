package br.com.makersweb.makersfood.infrastructure.configuration.usecases;

import br.com.makersweb.makersfood.application.state.create.CreateStateUseCase;
import br.com.makersweb.makersfood.application.state.create.DefaultCreateStateUseCase;
import br.com.makersweb.makersfood.application.state.delete.DefaultDeleteStateUseCase;
import br.com.makersweb.makersfood.application.state.delete.DeleteStateUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.get.DefaultGetStateByIdUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.get.GetStateByIdUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.list.DefaultListStateUseCase;
import br.com.makersweb.makersfood.application.state.retrieve.list.ListStateUseCase;
import br.com.makersweb.makersfood.application.state.update.DefaultUpdateStateUseCase;
import br.com.makersweb.makersfood.application.state.update.UpdateStateUseCase;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author aaristides
 */
@Configuration
public class StateUseCaseConfig {

    private final StateGateway stateGateway;

    public StateUseCaseConfig(final StateGateway stateGateway) {
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Bean
    public CreateStateUseCase createStateUseCase() {
        return new DefaultCreateStateUseCase(stateGateway);
    }

    @Bean
    public UpdateStateUseCase updateStateUseCase() {
        return new DefaultUpdateStateUseCase(stateGateway);
    }

    @Bean
    public GetStateByIdUseCase getStateByIdUseCase() {
        return new DefaultGetStateByIdUseCase(stateGateway);
    }

    @Bean
    public ListStateUseCase listStateUseCase() {
        return new DefaultListStateUseCase(stateGateway);
    }

    @Bean
    public DeleteStateUseCase deleteStateUseCase() {
        return new DefaultDeleteStateUseCase(stateGateway);
    }
}
