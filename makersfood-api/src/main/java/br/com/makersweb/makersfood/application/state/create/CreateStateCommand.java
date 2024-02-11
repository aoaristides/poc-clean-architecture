package br.com.makersweb.makersfood.application.state.create;

/**
 * @author aaristides
 * @param name
 */
public record CreateStateCommand(
        String name
) {

    public static CreateStateCommand with(final String name) {
        return new CreateStateCommand(name);
    }

}
