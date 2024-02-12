package br.com.makersweb.makersfood.application.state.retrieve.list;

import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateID;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param createdAt
 * @param updatedAt
 */
public record StateListOutput(
        StateID id,
        String name,
        Instant createdAt,
        Instant updatedAt
) {

    public static StateListOutput from(final State aState) {
        return new StateListOutput(aState.getId(), aState.getName(), aState.getCreatedAt(), aState.getUpdatedAt());
    }

}
