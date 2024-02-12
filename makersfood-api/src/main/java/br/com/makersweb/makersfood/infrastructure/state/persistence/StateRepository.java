package br.com.makersweb.makersfood.infrastructure.state.persistence;

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
public interface StateRepository extends JpaRepository<StateJpaEntity, String> {

    Page<StateJpaEntity> findAll(final Specification<StateJpaEntity> whereClause, final Pageable page);

    @Query(value = "select s.id from State s where s.id in :ids")
    List<String> existsByIds(@Param("ids") List<String> ids);

}
