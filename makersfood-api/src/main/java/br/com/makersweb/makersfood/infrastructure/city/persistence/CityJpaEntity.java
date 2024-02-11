package br.com.makersweb.makersfood.infrastructure.city.persistence;

import br.com.makersweb.makersfood.infrastructure.state.persistence.StateJpaEntity;
import jakarta.persistence.*;

import java.time.Instant;

/**
 * @author aaristides
 */
@Entity(name = "City")
@Table(name = "tb_cities")
public class CityJpaEntity {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(nullable = false)
    private StateJpaEntity state;

    @Column(name = "created_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false, columnDefinition = "TIMESTAMP")
    private Instant updatedAt;

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

    public StateJpaEntity getState() {
        return state;
    }

    public void setState(StateJpaEntity state) {
        this.state = state;
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
