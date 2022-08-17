package mk.ukim.finki.web.financeproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EntityFilterDto {
    private final String specificEntityLabel;
    private final String specificEntityWord;
}
