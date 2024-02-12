package br.com.makersweb.makersfood.infrastructure.city.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author aaristides
 * @param name
 * @param state
 */
public record UpdateCityRequest(
        @JsonProperty("name") String name,
        @JsonProperty("state") String state
) {
}
