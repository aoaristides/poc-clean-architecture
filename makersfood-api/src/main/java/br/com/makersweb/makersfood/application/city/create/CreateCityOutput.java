package br.com.makersweb.makersfood.application.city.create;

import br.com.makersweb.makersfood.domain.city.City;

/**
 * @author aaristides
 * @param id
 */
public record CreateCityOutput(
        String id
) {

    public static CreateCityOutput from(final String anId) {
        return new CreateCityOutput(anId);
    }

    public static CreateCityOutput from(final City aCity) {
        return new CreateCityOutput(aCity.getId().getValue());
    }

}
