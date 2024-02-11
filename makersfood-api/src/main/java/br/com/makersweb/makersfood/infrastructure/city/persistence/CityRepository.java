package br.com.makersweb.makersfood.infrastructure.city.persistence;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author aaristides
 */
public interface CityRepository extends JpaRepository<CityJpaEntity, String> {

    Page<CityJpaEntity> findAll(final Specification<CityJpaEntity> whereClause, final Pageable page);

    @Query(value = "select c.id from City c where c.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
