package racingcar.model.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.dto.RoundDto;
import racingcar.model.game.car.Car;
import racingcar.model.game.car.Cars;
import racingcar.model.game.name.Names;
import racingcar.model.game.round.Round;
import racingcar.model.strategy.MoveStrategy;

public final class RacingGame {

    private final Cars cars;
    private final Round round;
    private final List<RoundDto> snapshots = new ArrayList<>();

    private RacingGame(Cars cars, Round round) {
        this.cars = cars;
        this.round = round;
    }

    public static RacingGame of(Names names, Round round) {
        return new RacingGame(Cars.from(names), round);
    }

    public void play(MoveStrategy strategy) {
        while (round.hasNext()) {
            cars.moveCarsBy(strategy);
            recordSnapshot();
            round.next();
        }
    }

    private void recordSnapshot() {
        List<CarDto> states = cars.asList().stream()
                .map(car -> new CarDto(car.name(), car.distance()))
                .toList();
        snapshots.add(new RoundDto(states));
    }

    public List<RoundDto> snapshots() {
        return Collections.unmodifiableList(snapshots);
    }

    public ResultDto result() {
        List<String> winnerNames = winners().stream()
                .map(Car::name)
                .toList();
        return new ResultDto(winnerNames);
    }

    private List<Car> winners() {
        return cars.findWinners();
    }
}
