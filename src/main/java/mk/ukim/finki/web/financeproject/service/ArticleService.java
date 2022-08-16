package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ArticleService {

    List<Article> findAll(Date from, Date to, Source source);
    Optional<Article> findById(Long id);
    Page<Article> findPaginated(Pageable pageable);
}
