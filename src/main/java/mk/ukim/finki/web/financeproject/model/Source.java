package mk.ukim.finki.web.financeproject.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Source {
    @Id
    private Long id;
    public Source(){}
}
