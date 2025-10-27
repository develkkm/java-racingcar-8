package racingcar.config;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.controller.RacingController;
import racingcar.model.strategy.MoveStrategy;
import racingcar.view.InputView;
import racingcar.view.OutputView;

public class AppConfig {

    public MoveStrategy moveStrategy() {
        return () -> Randoms.pickNumberInRange(0, 9) >= 4;
    }

    public InputView inputView() {
        return new InputView();
    }

    public OutputView outputView() {
        return new OutputView();
    }

    public RacingController racingController() {
        return new RacingController(inputView(), outputView(), moveStrategy());
    }
}
