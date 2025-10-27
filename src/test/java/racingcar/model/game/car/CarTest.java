package racingcar.model.game.car;

import org.junit.jupiter.api.*;
import racingcar.model.game.name.Name;

import static org.junit.jupiter.api.Assertions.*;

class CarTest {

    private static Name validName;
    private static Car car;

    @BeforeAll
    static void setUp() {
        validName = Name.from("pobi");
        car = Car.from(validName);
    }

    @Nested
    @DisplayName("정상 동작")
    class ValidCase {

        @Test
        @DisplayName("유효한 Name으로 Car 객체를 생성한다")
        void shouldCreateCarWithValidName() {
            assertEquals(validName.name(), car.name());
            assertEquals(0, car.distance());
        }

        @Test
        @DisplayName("moveForward 호출 시 distance가 1 증가한다")
        void shouldIncreaseDistanceByOneWhenMoveForward() {
            car.moveForward();
            assertEquals(1, car.distance());
        }
    }
}
