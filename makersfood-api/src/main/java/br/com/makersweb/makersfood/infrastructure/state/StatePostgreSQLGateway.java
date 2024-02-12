package br.com.makersweb.makersfood.infrastructure.state;

import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;
import br.com.makersweb.makersfood.domain.state.State;
import br.com.makersweb.makersfood.domain.state.StateGateway;
import br.com.makersweb.makersfood.domain.state.StateID;
import br.com.makersweb.makersfood.infrastructure.state.persistence.StateJpaEntity;
import br.com.makersweb.makersfood.infrastructure.state.persistence.StateRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.StreamSupport;

import static br.com.makersweb.makersfood.infrastructure.utils.SpecificationUtils.like;

/**
 * @author aaristides
 */
@Service
public class StatePostgreSQLGateway implements StateGateway {

    private final StateRepository repository;

    public StatePostgreSQLGateway(final StateRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public State create(final State aState) {
        return save(aState);
    }

    @Override
    public void deleteById(final StateID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<State> findById(final StateID anId) {
        return this.repository.findById(anId.getValue()).map(StateJpaEntity::toAggregate);
    }

    @Override
    public State update(final State aState) {
        return save(aState);
    }

    @Override
    public Pagination<State> findAll(final SearchQuery aQuery) {
        // Paginação
        final var page = PageRequest.of(
                aQuery.page(),
                aQuery.perPage(),
                Sort.by(Sort.Direction.fromString(aQuery.direction()), aQuery.sort())
        );

        // Busca dinamica pelo criterio terms (name ou description)
        final var specifications = Optional.ofNullable(aQuery.terms())
                .filter(str -> !str.isBlank())
                .map(this::assembleSpecification)
                .orElse(null);

        final var pageResult = this.repository.findAll(Specification.where(specifications), page);

        return new Pagination<>(
                pageResult.getNumber(),
                pageResult.getSize(),
                pageResult.getTotalElements(),
                pageResult.map(StateJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<StateID> existsByIds(final Iterable<StateID> stateIDS) {
        final var ids = StreamSupport.stream(stateIDS.spliterator(), false).map(StateID::getValue).toList();
        return this.repository.existsByIds(ids).stream().map(StateID::from).toList();
    }

    private State save(final State aState) {
        return this.repository.save(StateJpaEntity.from(aState)).toAggregate();
    }

    private Specification<StateJpaEntity> assembleSpecification(final String str) {
        return like("name", str);
    }

}
