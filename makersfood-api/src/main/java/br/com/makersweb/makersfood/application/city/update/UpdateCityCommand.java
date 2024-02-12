package br.com.makersweb.makersfood.application.city.update;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param state
 */
public record UpdateCityCommand(
        String id,
        String name,
        String state
) {

    public static UpdateCityCommand with(final String anId, final String name, final String state) {
        return new UpdateCityCommand(anId, name, state);
    }

}
