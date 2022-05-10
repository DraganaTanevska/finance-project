package mk.ukim.finki.web.financeproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Data
@Entity
public class Article {
    @Id
    private Long id;
    private String title;
    private String text;
    private String url;
    private Date date;
    @ManyToOne
    private Source source;
    public Article(){}
}
