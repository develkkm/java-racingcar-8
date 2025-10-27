package racingcar.dto;

import java.util.List;

public record RoundDto(List<CarDto> cars) {
    public RoundDto(List<CarDto> cars) {
        this.cars = List.copyOf(cars);
    }
}
