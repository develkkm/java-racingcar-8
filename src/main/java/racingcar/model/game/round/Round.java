package racingcar.model.game.round;

public class Round {
    private static final String INVALID_ROUND_MESSAGE = "시도 횟수는 1 이상이어야 합니다.";

    private final int totalRounds;
    private int currentRound = 0;

    private Round(int totalRounds) {
        validate(totalRounds);
        this.totalRounds = totalRounds;
    }

    public static Round from(String input) {
        validateEmpty(input);
        int totalRounds = parseToInt(input);
        return new Round(totalRounds);
    }

    private static void validateEmpty(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(INVALID_ROUND_MESSAGE);
        }
    }

    private static int parseToInt(String input) {
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(INVALID_ROUND_MESSAGE);
        }
    }

    private void validate(int totalRounds) {
        if (totalRounds < 1) {
            throw new IllegalArgumentException(INVALID_ROUND_MESSAGE);
        }
    }

    public boolean hasNext() {
        return currentRound < totalRounds;
    }

    public void next() {
        currentRound++;
    }

}
