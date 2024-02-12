package br.com.makersweb.makersfood.infrastructure.city.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param state
 */
public record CityListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("state_id") String state,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
