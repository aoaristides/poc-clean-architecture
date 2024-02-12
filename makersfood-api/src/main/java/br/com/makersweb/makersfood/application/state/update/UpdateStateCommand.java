package br.com.makersweb.makersfood.application.state.update;

/**
 * @author aaristides
 * @param id
 * @param name
 */
public record UpdateStateCommand(
        String id,
        String name,
        String description
) {

    public static UpdateStateCommand with(final String anId, final String name, final String description) {
        return new UpdateStateCommand(anId, name, description);
    }

}
