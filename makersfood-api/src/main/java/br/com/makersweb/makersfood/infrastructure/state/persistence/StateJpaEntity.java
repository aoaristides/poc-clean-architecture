package br.com.makersweb.makersfood.infrastructure.state.persistence;

import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;

/**
 * @author aaristides
 */
@Entity(name = "State")
@Table(name = "tb_states")
public class StateJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

    public StateJpaEntity() {}

    private StateJpaEntity(
            final String id,
            final String name,
            final String description,
            final Instant createdAt,
            final Instant updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    private StateJpaEntity(final String id) {
        this.id = id;
    }

    public static StateJpaEntity from(final State aState) {
        return new StateJpaEntity(
                aState.getId().getValue(),
                aState.getName(),
                aState.getDescription(),
                aState.getCreatedAt(),
                aState.getUpdatedAt()
        );
    }

    public static StateJpaEntity from(final String anId) {
        return new StateJpaEntity(anId);
    }

    public State toAggregate() {
        return State.with(
                StateID.from(getId()),
                getName(),
                getDescription(),
                getCreatedAt(),
                getUpdatedAt()
        );
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }
}
