package racingcar.model.game;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import racingcar.dto.RoundDto;
import racingcar.model.game.name.Names;
import racingcar.model.game.round.Round;
import racingcar.model.strategy.MoveStrategy;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class RacingGameTest {

    @Nested
    @DisplayName("일반 전략 기반 동작 테스트")
    class WithRacingMoveStrategy {

        @Test
        @DisplayName("라운드 수만큼 스냅샷이 생성되어야 한다.")
        void shouldRecordSnapshotsPerRound() {
            RacingGame game = RacingGame.of(Names.from("pobi,woni"), Round.from("5"));
            game.play(new AlwaysTrueStrategy());

            assertEquals(5, game.snapshots().size());
        }
    }

    @Nested
    @DisplayName("전략 호출 및 스냅샷 검증 테스트")
    class DeterministicStrategy {

        @Test
        @DisplayName("항상 이동하는 전략이면 매 라운드 후 거리 증가해야 한다.")
        void shouldMoveEveryRoundWithAlwaysTrueStrategy() {
            RacingGame game = RacingGame.of(Names.from("a,b"), Round.from("3"));
            game.play(new AlwaysTrueStrategy());

            List<RoundDto> snapshots = game.snapshots();
            assertEquals(3, snapshots.size());
        }

        @Test
        @DisplayName("항상 멈추는 전략이면 모든 거리 0이어야 한다.")
        void shouldNotMoveWithAlwaysFalseStrategy() {
            RacingGame game = RacingGame.of(Names.from("a,b"), Round.from("2"));
            game.play(new AlwaysFalseStrategy());

            List<RoundDto> snapshots = game.snapshots();
            assertTrue(snapshots.stream()
                    .allMatch(round -> round.cars().stream()
                            .allMatch(car -> car.distance() == 0)));
        }

        @Test
        @DisplayName("snapshots()는 수정 불가능해야 한다.")
        void shouldReturnUnmodifiableSnapshots() {
            RacingGame game = RacingGame.of(Names.from("a,b"), Round.from("1"));
            game.play(new AlwaysTrueStrategy());

            List<RoundDto> snapshots = game.snapshots();
            assertThrows(UnsupportedOperationException.class,
                    () -> snapshots.add(snapshots.get(0)));
        }
    }

    static class AlwaysTrueStrategy implements MoveStrategy {
        @Override
        public boolean movable() {
            return true;
        }
    }

    static class AlwaysFalseStrategy implements MoveStrategy {
        @Override
        public boolean movable() {
            return false;
        }
    }
}
