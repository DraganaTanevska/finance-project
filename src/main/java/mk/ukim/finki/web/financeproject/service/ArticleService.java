package mk.ukim.finki.web.financeproject.service;

import mk.ukim.finki.web.financeproject.model.Article;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface ArticleService {

    List<Article> findAll(Date from, Date to, Long id);
    Optional<Article> findById(Long id);
}
