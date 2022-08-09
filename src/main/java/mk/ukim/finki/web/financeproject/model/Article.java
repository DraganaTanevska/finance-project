package mk.ukim.finki.web.financeproject.model;

import lombok.Data;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity()
@Table(name="api_data_with_sentiment")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private String title;
    private String text;
    private String url;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Source source;
    private String sentiment;
    @OneToMany
    private List<NamedEntities> namedEntitiesList;
    public Article(){}
}
