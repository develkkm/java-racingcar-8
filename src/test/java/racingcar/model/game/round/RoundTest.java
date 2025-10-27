package racingcar.model.game.round;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RoundTest {

    @Nested
    @DisplayName("정상 동작")
    class ValidCase {

        @Test
        @DisplayName("유효한 숫자 입력이면 Round 객체를 생성해야 한다.")
        void shouldCreateRoundWhenValidNumberInput() {
            Round round = Round.from("3");
            assertTrue(round.hasNext());
        }

        @Test
        @DisplayName("입력값의 앞뒤 공백은 제거되어야 한다.")
        void shouldTrimSpacesAroundInput() {
            Round round = Round.from("   2  ");
            assertTrue(round.hasNext());
        }

        @Test
        @DisplayName("hasNext는 남은 라운드가 있을 때 true를 반환해야 한다.")
        void shouldReturnTrueWhenHasNextRound() {
            Round round = Round.from("2");
            assertTrue(round.hasNext());
        }

        @Test
        @DisplayName("next() 호출 시 라운드가 진행되어 hasNext가 false가 될 수 있어야 한다.")
        void shouldIncreaseCurrentRoundWhenNextCalled() {
            Round round = Round.from("1");
            assertTrue(round.hasNext());

            round.next();

            assertFalse(round.hasNext());
        }
    }

    @Nested
    @DisplayName("예외 발생 테스트")
    class ExceptionCase {

        @Test
        @DisplayName("입력이 null이면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenInputIsNull() {
            assertThrows(IllegalArgumentException.class, () -> Round.from(null));
        }

        @Test
        @DisplayName("입력이 빈 문자열이면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenInputIsEmpty() {
            assertThrows(IllegalArgumentException.class, () -> Round.from(""));
        }

        @Test
        @DisplayName("입력이 공백만 있으면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenInputIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> Round.from("   "));
        }

        @Test
        @DisplayName("입력이 숫자가 아니면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenInputIsNotNumber() {
            assertThrows(IllegalArgumentException.class, () -> Round.from("abc"));
            assertThrows(IllegalArgumentException.class, () -> Round.from("3a"));
        }

        @Test
        @DisplayName("입력 숫자가 1 미만이면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenRoundIsLessThanOne() {
            assertThrows(IllegalArgumentException.class, () -> Round.from("0"));
            assertThrows(IllegalArgumentException.class, () -> Round.from("-5"));
        }
    }
}
