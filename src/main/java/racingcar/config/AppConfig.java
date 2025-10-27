package racingcar.config;

import racingcar.controller.RacingController;
import racingcar.model.generator.RandomNumberGenerator;
import racingcar.model.strategy.MoveStrategy;
import racingcar.model.strategy.RacingMoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {
    private static final int MIN_NUMBER = 0;
    private static final int MAX_NUMBER = 9;
    private static final int THRESHOLD = 4;

    public MoveStrategy moveStrategy() {
        return new RacingMoveStrategy(new RandomNumberGenerator(MIN_NUMBER,MAX_NUMBER),THRESHOLD);
    }

    public RacingController racingController() {
        return new RacingController(inputView(), outputView(), moveStrategy());
    }

    private InputView inputView() {
        return new InputView();
    }

    private OutputView outputView() {
        return new OutputView();
    }
}
