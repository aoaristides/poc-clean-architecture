package br.com.makersweb.makersfood.application.city.retrieve.get;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityID;
import br.com.makersweb.makersfood.domain.state.StateID;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param state
 * @param createdAt
 * @param updatedAt
 */
public record CityOutput(
        CityID id,
        String name,
        StateID state,
        Instant createdAt,
        Instant updatedAt
) {

    public static CityOutput from(final City aCity) {
        return new CityOutput(aCity.getId(), aCity.getName(), aCity.getState(), aCity.getCreatedAt(), aCity.getUpdatedAt());
    }

}
