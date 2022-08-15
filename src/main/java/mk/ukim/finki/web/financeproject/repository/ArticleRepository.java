package mk.ukim.finki.web.financeproject.repository;

import com.querydsl.core.types.Predicate;
import mk.ukim.finki.web.financeproject.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long>, QuerydslPredicateExecutor<Article> {
    List<Article> findAll(Predicate predicate);
}
