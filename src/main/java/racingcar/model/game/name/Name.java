package racingcar.model.game.name;

public final class Name {
    private static final int MAX_LENGTH = 5;
    private static final String EMPTY_ERROR_MESSAGE = "자동차 이름은 비어 있을 수 없습니다.";
    private static final String LENGTH_ERROR_MESSAGE = "자동차 이름은 5자를 초과할 수 없습니다.";

    private final String name;

    private Name(String name) {
        validateName(name);
        this.name = name;
    }

    public static Name from(String rawName) {
        validateNull(rawName);
        String trimmed = rawName.trim();
        return new Name(trimmed);
    }

    private static void validateNull(String rawName) {
        if (rawName == null) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateName(String name) {
        validateNotEmpty(name);
        validateLength(name);
    }

    private void validateNotEmpty(String name) {
        if (name.isEmpty()) {
            throw new IllegalArgumentException(EMPTY_ERROR_MESSAGE);
        }
    }

    private void validateLength(String name) {
        if (name.length() > MAX_LENGTH) {
            throw new IllegalArgumentException(LENGTH_ERROR_MESSAGE);
        }
    }

    public String name() {
        return name;
    }

}
