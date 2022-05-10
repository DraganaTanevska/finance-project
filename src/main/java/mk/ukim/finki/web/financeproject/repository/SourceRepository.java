package mk.ukim.finki.web.financeproject.repository;

import mk.ukim.finki.web.financeproject.model.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceRepository extends JpaRepository<Source, Long> {

}
