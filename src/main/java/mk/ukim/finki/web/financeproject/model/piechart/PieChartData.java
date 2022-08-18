package mk.ukim.finki.web.financeproject.model.piechart;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PieChartData {
    private List<String> labels;
    private List<Dataset> datasets;
}
