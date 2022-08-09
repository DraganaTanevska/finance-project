package mk.ukim.finki.web.financeproject.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "")
public class NamedEntities {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    private Long id_news;
    private String label;
    private String word;
    public NamedEntities() {
    }
}
