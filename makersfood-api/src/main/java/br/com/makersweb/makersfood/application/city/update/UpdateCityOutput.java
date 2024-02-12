package br.com.makersweb.makersfood.application.city.update;

import br.com.makersweb.makersfood.domain.city.City;

/**
 * @author aaristides
 * @param id
 */
public record UpdateCityOutput(
        String id
) {

    public static UpdateCityOutput from(final String anId) {
        return new UpdateCityOutput(anId);
    }

    public static UpdateCityOutput from(final City aCity) {
        return new UpdateCityOutput(aCity.getId().getValue());
    }

}
