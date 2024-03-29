package br.com.makersweb.makersfood.infrastructure.state.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;

/**
 * @author aaristides
 * @param id
 * @param name
 * @param createdAt
 * @param updatedAt
 */
public record StateListResponse(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("description") String description,
        @JsonProperty("created_at") Instant createdAt,
        @JsonProperty("updated_at") Instant updatedAt
) {
}
