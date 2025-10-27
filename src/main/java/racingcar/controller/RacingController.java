package racingcar.controller;

import racingcar.model.game.RacingGame;
import racingcar.model.game.name.Names;
import racingcar.model.game.round.Round;
import racingcar.model.strategy.MoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class RacingController {

    private final InputView inputView;
    private final OutputView outputView;
    private final MoveStrategy moveStrategy;

    public RacingController(InputView inputView,
                            OutputView outputView,
                            MoveStrategy moveStrategy) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.moveStrategy = moveStrategy;
    }

    public void run() {
        try {
            executeGame();
        } catch (IllegalArgumentException e) {
            outputView.printError(e.getMessage());
            throw e;
        }
    }

    private void executeGame() {
        Names names = readNames();
        Round round = readRound();

        RacingGame game = RacingGame.of(names, round);
        game.play(moveStrategy);
        printResult(game);
    }

    private Names readNames() {
        String nameInput = inputView.readNames();
        return Names.from(nameInput);
    }

    private Round readRound() {
        String roundInput = inputView.readRound();
        return Round.from(roundInput);
    }

    private void printResult(RacingGame game) {
        outputView.printResultMessage();
        outputView.printRoundSnapshots(game.snapshots());
        outputView.printWinners(game.result());
    }
}
