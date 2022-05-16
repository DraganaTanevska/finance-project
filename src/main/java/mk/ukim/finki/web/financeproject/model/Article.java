package mk.ukim.finki.web.financeproject.model;

import lombok.Data;
import mk.ukim.finki.web.financeproject.model.enumerations.Source;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity()
@Table(name="api_data")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "index")
    private Long id;
    private String title;
    private String text;
    private String url;
    private Date date;
    @Enumerated(EnumType.STRING)
    private Source source;
    public Article(){}
}
