package mk.ukim.finki.web.financeproject.model.piechart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dataset {
    private List<Long> data;
    private List<String> backgroundColor;
    private List<String> hoverBackgroundColor;
}
