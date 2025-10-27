package racingcar.model.generator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomNumberGeneratorTest {

    @Test
    @DisplayName("generate()는 지정된 범위 내의 숫자를 생성해야 한다.")
    void shouldGenerateNumbersWithinRange() {
        RandomNumberGenerator generator = createRandomNumberGenerator(0,9);
        int number = generator.generate();
        assertTrue(number >= 0 && number <= 9, "범위를 벗어난 숫자가 생성됨: " + number);
    }

    @Test
    @DisplayName("min과 max가 같을 때 항상 같은 값을 반환해야 한다.")
    void shouldAlwaysReturnSameNumberWhenMinEqualsMax() {
        RandomNumberGenerator generator = createRandomNumberGenerator(7,7);
        assertEquals(7, generator.generate());
    }

    private static RandomNumberGenerator createRandomNumberGenerator(int min, int max) {
        return new RandomNumberGenerator(min, max);
    }
}
