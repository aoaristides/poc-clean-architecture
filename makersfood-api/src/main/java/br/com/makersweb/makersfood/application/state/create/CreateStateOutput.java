package br.com.makersweb.makersfood.application.state.create;

import br.com.makersweb.makersfood.domain.state.State;

/**
 * @author aaristides
 * @param id
 */
public record CreateStateOutput(
        String id
) {

    public static CreateStateOutput from(final String anId) {
        return new CreateStateOutput(anId);
    }

    public static CreateStateOutput from(final State aState) {
        return new CreateStateOutput(aState.getId().getValue());
    }

}
