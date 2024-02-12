package br.com.makersweb.makersfood.infrastructure.state.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param name
 */
public record CreateStateRequest(
        @JsonProperty("name") String name
) {
}
