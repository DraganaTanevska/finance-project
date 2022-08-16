package mk.ukim.finki.web.financeproject.model;

import lombok.Data;
import mk.ukim.finki.web.financeproject.model.enumerations.SourceApi;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity

@Table(name = "api_data_with_sentiment2")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String text;
    private String url;
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "source")
    private SourceApi sourceApi;
    private String sentiment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "news")
    private List<NamedEntities> namedEntitiesList;
}
