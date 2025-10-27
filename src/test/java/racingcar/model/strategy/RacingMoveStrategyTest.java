package racingcar.model.strategy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.model.generator.NumberGenerator;

import static org.junit.jupiter.api.Assertions.*;

class RacingMoveStrategyTest {

    @Nested
    @DisplayName("이동 가능 조건 테스트")
    class MovableCondition {

        @Test
        @DisplayName("생성된 숫자가 threshold 이상이면 true를 반환해야 한다.")
        void shouldReturnTrueWhenNumberIsGreaterOrEqualToThreshold() {
            NumberGenerator generator = new FixedNumberGenerator(5);
            RacingMoveStrategy strategy = new RacingMoveStrategy(generator, 4);

            assertTrue(strategy.movable());
        }

        @Test
        @DisplayName("생성된 숫자가 threshold보다 작으면 false를 반환해야 한다.")
        void shouldReturnFalseWhenNumberIsLessThanThreshold() {
            NumberGenerator generator = new FixedNumberGenerator(3);
            RacingMoveStrategy strategy = new RacingMoveStrategy(generator, 4);

            assertFalse(strategy.movable());
        }

        @Test
        @DisplayName("생성된 숫자가 threshold와 같을 때도 true를 반환해야 한다.")
        void shouldReturnTrueWhenNumberEqualsThreshold() {
            NumberGenerator generator = new FixedNumberGenerator(4);
            RacingMoveStrategy strategy = new RacingMoveStrategy(generator, 4);

            assertTrue(strategy.movable());
        }
    }

    static class FixedNumberGenerator implements NumberGenerator {
        private final int fixedNumber;

        FixedNumberGenerator(int fixedNumber) {
            this.fixedNumber = fixedNumber;
        }

        @Override
        public int generate() {
            return fixedNumber;
        }
    }
}
