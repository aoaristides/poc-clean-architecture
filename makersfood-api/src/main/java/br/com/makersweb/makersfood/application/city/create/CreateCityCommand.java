package br.com.makersweb.makersfood.application.city.create;

/**
 * @author aaristides
 * @param name
 * @param state
 */
public record CreateCityCommand(
        String name,
        String state
) {

    public static CreateCityCommand with(final String name, final String state) {
        return new CreateCityCommand(name, state);
    }

}
