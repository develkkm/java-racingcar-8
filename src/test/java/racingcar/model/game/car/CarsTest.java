package racingcar.model.game.car;

import org.junit.jupiter.api.*;
import racingcar.model.game.name.Names;
import racingcar.model.strategy.MoveStrategy;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class CarsTest {

    private static Cars cars;

    @BeforeEach
    void setUp() {
        cars = Cars.from(Names.from("pobi,woni,jun"));
    }

    @Nested
    @DisplayName("정상 동작")
    class ValidCase {

        @Test
        @DisplayName("moveCarsBy 호출 시 전략 결과가 true인 자동차만 전진한다")
        void shouldMoveOnlyCarsThatSatisfyStrategy() {
            MoveStrategy alternating = getMoveStrategy();
            cars.moveCarsBy(alternating);

            List<Car> list = cars.asList();
            assertEquals(1, list.get(0).distance());
            assertEquals(0, list.get(1).distance());
            assertEquals(1, list.get(2).distance());
        }

        private MoveStrategy getMoveStrategy() {
            return new MoveStrategy() {
                private final boolean[] results = {true, false, true};
                private int index = 0;

                @Override
                public boolean movable() {
                    return results[index++ % results.length];
                }
            };
        }

        @Test
        @DisplayName("최대 위치가 같은 차들이 모두 우승자로 반환된다")
        void shouldReturnAllCarsWithMaxDistanceAsWinners() {
            List<Car> list = cars.asList();

            // a: 2칸, b: 2칸, c: 1칸
            list.get(0).moveForward();
            list.get(0).moveForward();
            list.get(1).moveForward();
            list.get(1).moveForward();
            list.get(2).moveForward();

            List<Car> winners = cars.findWinners();

            Set<Car> expected = Set.of(list.get(0), list.get(1));
            Set<Car> actual = Set.copyOf(winners);
            assertEquals(expected, actual);
        }

        @Test
        @DisplayName("모두 0칸이면 전체가 우승자로 반환된다")
        void shouldReturnAllCarsAsWinnersWhenAllZero() {
            List<Car> winners = cars.findWinners();

            assertEquals(Set.copyOf(cars.asList()), Set.copyOf(winners));
        }
    }
}
