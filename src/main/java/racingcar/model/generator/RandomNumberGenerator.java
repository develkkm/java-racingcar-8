package racingcar.model.generator;

import camp.nextstep.edu.missionutils.Randoms;

public final class RandomNumberGenerator implements NumberGenerator {
    private final int min;
    private final int max;

    public RandomNumberGenerator(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public int generate() {
        return Randoms.pickNumberInRange(min, max);
    }
}

