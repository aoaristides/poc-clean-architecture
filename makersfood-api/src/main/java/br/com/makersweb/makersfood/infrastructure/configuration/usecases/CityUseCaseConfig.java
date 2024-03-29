package br.com.makersweb.makersfood.infrastructure.configuration.usecases;

import br.com.makersweb.makersfood.application.city.create.CreateCityUseCase;
import br.com.makersweb.makersfood.application.city.create.DefaultCreateCityUseCase;
import br.com.makersweb.makersfood.application.city.delete.DefaultDeleteCityUseCase;
import br.com.makersweb.makersfood.application.city.delete.DeleteCityUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.get.DefaultGetCityByIdUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.get.GetCityByIdUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.list.DefaultListCitiesUseCase;
import br.com.makersweb.makersfood.application.city.retrieve.list.ListCitiesUseCase;
import br.com.makersweb.makersfood.application.city.update.DefaultUpdateCityUseCase;
import br.com.makersweb.makersfood.application.city.update.UpdateCityUseCase;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

/**
 * @author aaristides
 */
@Configuration
public class CityUseCaseConfig {

    private final CityGateway cityGateway;
    private final StateGateway stateGateway;

    public CityUseCaseConfig(
            final CityGateway cityGateway,
            final StateGateway stateGateway
    ) {
        this.cityGateway = Objects.requireNonNull(cityGateway);
        this.stateGateway = Objects.requireNonNull(stateGateway);
    }

    @Bean
    public CreateCityUseCase createCityUseCase() {
        return new DefaultCreateCityUseCase(cityGateway, stateGateway);
    }

    @Bean
    public UpdateCityUseCase updateCityUseCase() {
        return new DefaultUpdateCityUseCase(cityGateway, stateGateway);
    }

    @Bean
    public GetCityByIdUseCase getCityByIdUseCase() {
        return new DefaultGetCityByIdUseCase(cityGateway);
    }

    @Bean
    public ListCitiesUseCase listCitiesUseCase() {
        return new DefaultListCitiesUseCase(cityGateway);
    }

    @Bean
    public DeleteCityUseCase deleteCityUseCase() {
        return new DefaultDeleteCityUseCase(cityGateway);
    }
}
