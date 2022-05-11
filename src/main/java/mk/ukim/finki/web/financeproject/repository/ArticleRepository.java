package mk.ukim.finki.web.financeproject.repository;
import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findArticlesByDateBetween(Date from, Date to);
    List<Article> findArticlesByDateBefore(Date to);
    List<Article> findArticlesByDateAfter(Date from);
    List<Article> findArticlesByDateBetweenAndSource(Date from, Date to, Source source);
    List<Article> findArticlesByDateBeforeAndSource(Date to, Source source);
    List<Article> findArticlesByDateAfterAndSource(Date from, Source source);
    List<Article> findArticlesBySource(Source source);
}
