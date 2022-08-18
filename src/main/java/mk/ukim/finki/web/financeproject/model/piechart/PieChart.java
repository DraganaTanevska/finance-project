package mk.ukim.finki.web.financeproject.model.piechart;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieChart {
    String type;
    PieChartData data;
    Options options;
}
