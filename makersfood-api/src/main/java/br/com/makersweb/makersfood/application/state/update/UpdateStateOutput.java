package br.com.makersweb.makersfood.application.state.update;

import br.com.makersweb.makersfood.domain.state.State;

/**
 * @author aaristides
 * @param id
 */
public record UpdateStateOutput(
        String id
) {

    public static UpdateStateOutput from(final String anId) {
        return new UpdateStateOutput(anId);
    }

    public static UpdateStateOutput from(final State aState) {
        return new UpdateStateOutput(aState.getId().getValue());
    }

}
