package racingcar.view;

import java.util.List;
import racingcar.dto.CarDto;
import racingcar.dto.ResultDto;
import racingcar.dto.RoundDto;

public class OutputView {

    private static final String EXECUTION_RESULT_MESSAGE = "실행 결과";
    private static final String WINNER_PREFIX = "최종 우승자 : ";

    public void printResultMessage() {
        printBlankLine();
        System.out.println(EXECUTION_RESULT_MESSAGE);
    }

    public void printRoundSnapshots(List<RoundDto> snapshots) {
        for (RoundDto snapshot : snapshots) {
            printSingleRound(snapshot);
            printBlankLine();
        }
    }

    private void printBlankLine() {
        System.out.println();
    }

    private void printSingleRound(RoundDto snapshot) {
        for (CarDto car : snapshot.cars()) {
            System.out.println(formatCarProgress(car));
        }
    }

    private String formatCarProgress(CarDto car) {
        return String.format("%s : %s", car.name(), "-".repeat(car.distance()));
    }

    public void printWinners(ResultDto result) {
        System.out.println(WINNER_PREFIX + String.join(", ", result.winners()));
    }

    public void printError(String message) {
        System.out.println("[ERROR] " + message);
    }
}
