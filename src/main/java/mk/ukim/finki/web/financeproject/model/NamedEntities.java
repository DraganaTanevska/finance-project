package mk.ukim.finki.web.financeproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "named_entities2")
public class NamedEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_news")
    private Article news;
    private String label;
    private String word;
}
