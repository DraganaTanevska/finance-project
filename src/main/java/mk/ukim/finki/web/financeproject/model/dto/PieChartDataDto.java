package mk.ukim.finki.web.financeproject.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PieChartDataDto {
    private String label;
    private Long value;
    private String backgroundColor;
    private String hoverBackgroundColor;
}
