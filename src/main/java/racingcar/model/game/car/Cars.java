package racingcar.model.game.car;

import java.util.List;
import java.util.stream.Collectors;
import racingcar.model.game.name.Names;
import racingcar.model.strategy.MoveStrategy;

public class Cars {
    private final List<Car> cars;

    private Cars(List<Car> cars) {
        this.cars = List.copyOf(cars);
    }

    public static Cars from(Names names) {
        List<Car> cars = names.asList().stream()
                .map(Car::from)
                .collect(Collectors.toList());
        return new Cars(cars);
    }

    public void moveCarsBy(MoveStrategy strategy) {
        cars.forEach(car -> {
            if (strategy.movable()) {
                car.moveForward();
            }
        });
    }

    public List<Car> findWinners() {
        int maxDistance = findMaxDistance();

        return cars.stream()
                .filter(car -> car.distance() == maxDistance)
                .collect(Collectors.toList());
    }

    private int findMaxDistance() {
        return cars.stream()
                .mapToInt(Car::distance)
                .max()
                .orElse(0);
    }

    public List<Car> asList() {
        return cars;
    }

}
