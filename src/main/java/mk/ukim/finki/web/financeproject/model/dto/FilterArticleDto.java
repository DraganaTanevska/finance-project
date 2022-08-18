package mk.ukim.finki.web.financeproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import mk.ukim.finki.web.financeproject.model.Article;
import org.springframework.data.domain.Page;

@AllArgsConstructor
@Getter
@Setter
public class FilterArticleDto {
    Page<Article> articlePage;
    String pieChartData;
}
