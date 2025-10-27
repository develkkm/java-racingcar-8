package racingcar.model.game.name;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class NamesTest {

    @Nested
    @DisplayName("정상 동작")
    class ValidCase {

        @Test
        @DisplayName("두 개 이상의 유효한 이름이면 Names 객체를 생성해야 한다.")
        void shouldCreateNamesWhenInputHasAtLeastTwoValidNames() {
            List<Name> list = createNameList();

            assertEquals(2, list.size());
            assertEquals("pobi", list.get(0).name());
            assertEquals("woni", list.get(1).name());
        }

        @Test
        @DisplayName("반환된 리스트는 수정 불가능해야 한다.")
        void shouldReturnUnmodifiableList() {
            List<Name> list = createNameList();

            assertThrows(UnsupportedOperationException.class, () -> list.add(Name.from("june")));
        }

        private static List<Name> createNameList() {
            Names names = Names.from("pobi,woni");
            return names.asList();
        }
    }

    @Nested
    @DisplayName("예외 발생")
    class ExceptionCase {

        @Test
        @DisplayName("입력이 null이면 예외를 던져야 한다.")
        void shouldThrowWhenInputIsNull() {
            assertThrows(IllegalArgumentException.class, () -> Names.from(null));
        }

        @Test
        @DisplayName("입력이 빈 문자열이거나 공백만 있으면 예외를 던져야 한다.")
        void shouldThrowWhenInputIsBlank() {
            assertThrows(IllegalArgumentException.class, () -> Names.from(""));
            assertThrows(IllegalArgumentException.class, () -> Names.from("   "));
        }

        @Test
        @DisplayName("이름이 한 개뿐이면 예외를 던져야 한다.")
        void shouldThrowWhenOnlyOneName() {
            assertThrows(IllegalArgumentException.class, () -> Names.from("pobi"));
        }

        @Test
        @DisplayName("중복된 이름이 있으면 예외를 던져야 한다.")
        void shouldThrowWhenNamesAreDuplicated() {
            assertThrows(IllegalArgumentException.class, () -> Names.from("pobi,pobi"));
        }

        @Test
        @DisplayName("중복된 이름이 공백을 포함해도 동일하게 판단되어 예외를 던져야 한다.")
        void shouldThrowWhenNamesAreDuplicatedEvenWithSpaces() {
            assertThrows(IllegalArgumentException.class, () -> Names.from("pobi,  pobi "));
        }
    }
}
