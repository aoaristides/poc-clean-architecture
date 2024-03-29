package br.com.makersweb.makersfood.infrastructure.city.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

/**
 * @author aaristides
 */
public interface CityRepository extends JpaRepository<CityJpaEntity, String> {

    Page<CityJpaEntity> findAll(final Specification<CityJpaEntity> whereClause, final Pageable page);

    @Query(value = "select c.id from City c where c.id in :ids")
    boolean existsByIds(@Param("ids") List<String> ids);

    @Query(value = "select c.id from City c where c.id in :ids")
    List<String> findByIds(@Param("ids") List<String> ids);

    Optional<CityJpaEntity> findByName(final String name);

}
