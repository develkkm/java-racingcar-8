package racingcar.model.strategy;

import racingcar.model.generator.NumberGenerator;

public final class RacingMoveStrategy implements MoveStrategy {
    private final int threshold;
    private final NumberGenerator generator;

    public RacingMoveStrategy(NumberGenerator generator, int threshold) {
        this.generator = generator;
        this.threshold = threshold;
    }

    @Override
    public boolean movable() {
        return generator.generate() >= threshold;
    }
}

