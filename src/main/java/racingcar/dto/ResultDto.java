package racingcar.dto;

import java.util.List;

public record ResultDto(List<String> winners) {
    public ResultDto(List<String> winners) {
        this.winners = List.copyOf(winners);
    }
}
