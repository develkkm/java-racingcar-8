package racingcar.model.game.name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {

    @Nested
    @DisplayName("정상 동작")
    class ValidCase {

        @Test
        @DisplayName("유효한 이름이면 Name 객체를 생성해야 한다.")
        void shouldCreateNameWhenValid() {
            Name name = Name.from("pobi");
            assertEquals("pobi", name.name());
        }

        @Test
        @DisplayName("이름의 앞뒤 공백은 제거되어야 한다.")
        void shouldTrimSpacesAroundName() {
            Name name = Name.from("  pobi  ");
            assertEquals("pobi", name.name());
        }
    }

    @Nested
    @DisplayName("예외 발생")
    class ExceptionCase {

        @Test
        @DisplayName("이름이 null이면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenNameIsNull() {
            assertThrows(IllegalArgumentException.class,
                    () -> Name.from(null));
        }

        @Test
        @DisplayName("이름이 빈 문자열이면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenNameIsEmpty() {
            assertThrows(IllegalArgumentException.class,
                    () -> Name.from(""));
        }

        @Test
        @DisplayName("이름이 공백만 포함하면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenNameContainsOnlySpaces() {
            assertThrows(IllegalArgumentException.class,
                    () -> Name.from("   "));
        }

        @Test
        @DisplayName("이름 길이가 5자를 초과하면 예외를 던져야 한다.")
        void shouldThrowExceptionWhenNameExceedsMaxLength() {
            assertThrows(IllegalArgumentException.class,
                    () -> Name.from("abcdef"));
        }
    }
}

