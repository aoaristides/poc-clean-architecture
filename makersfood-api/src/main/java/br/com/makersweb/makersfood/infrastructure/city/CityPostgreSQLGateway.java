package br.com.makersweb.makersfood.infrastructure.city;

import br.com.makersweb.makersfood.domain.city.City;
import br.com.makersweb.makersfood.domain.city.CityGateway;
import br.com.makersweb.makersfood.domain.city.CityID;
import br.com.makersweb.makersfood.domain.pagination.Pagination;
import br.com.makersweb.makersfood.domain.pagination.SearchQuery;
import br.com.makersweb.makersfood.infrastructure.city.persistence.CityJpaEntity;
import br.com.makersweb.makersfood.infrastructure.city.persistence.CityRepository;
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
public class CityPostgreSQLGateway implements CityGateway {

    private final CityRepository repository;

    public CityPostgreSQLGateway(final CityRepository repository) {
        this.repository = Objects.requireNonNull(repository);
    }

    @Override
    public City create(final City city) {
        return save(city);
    }

    @Override
    public void deleteById(final CityID anId) {
        final String anIdValue = anId.getValue();
        if (this.repository.existsById(anIdValue)) {
            this.repository.deleteById(anIdValue);
        }
    }

    @Override
    public Optional<City> findById(final CityID anId) {
        return this.repository.findById(anId.getValue()).map(CityJpaEntity::toAggregate);
    }

    @Override
    public City update(final City city) {
        return save(city);
    }

    @Override
    public Pagination<City> findAll(final SearchQuery aQuery) {
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
                pageResult.map(CityJpaEntity::toAggregate).toList()
        );
    }

    @Override
    public List<CityID> existsByIds(final Iterable<CityID> cityIDS) {
        final var ids = StreamSupport.stream(cityIDS.spliterator(), false)
                .map(CityID::getValue)
                .toList();
        return this.repository.existsByIds(ids).stream().map(CityID::from).toList();
    }

    private City save(final City aCity) {
        return this.repository.save(CityJpaEntity.from(aCity)).toAggregate();
    }

    private Specification<CityJpaEntity> assembleSpecification(final String str) {
        return like("name", str);
    }
}
