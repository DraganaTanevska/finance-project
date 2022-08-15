package mk.ukim.finki.web.financeproject.repository;

import mk.ukim.finki.web.financeproject.model.NamedEntities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NamedEntityRepository extends JpaRepository<NamedEntities, Long>, QuerydslPredicateExecutor<NamedEntities> {
    @Query("select n.label from NamedEntities n group by n.label")
    List<String> getAllEntities ();
}
