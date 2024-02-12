package br.com.makersweb.makersfood.application.state.create;

/**
 * @author aaristides
 * @param name
 */
public record CreateStateCommand(
        String name,
        String description
) {

    public static CreateStateCommand with(final String name, final String description) {
        return new CreateStateCommand(name, description);
    }

}
