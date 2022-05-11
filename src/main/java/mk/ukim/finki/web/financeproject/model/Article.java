package mk.ukim.finki.web.financeproject.model;

import lombok.Data;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    private Source source;
    public Article(){}
}
