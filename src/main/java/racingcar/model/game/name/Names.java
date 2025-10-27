package racingcar.model.game.name;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Names {
    private static final String SEPARATOR = ",";
    private static final String EMPTY_INPUT_ERROR_MESSAGE = "자동차 이름을 최소 1개 이상 입력해야 합니다.";
    private static final String TOO_FEW_CARS_MESSAGE = "자동차는 최소 2대 이상이어야 합니다.";
    private static final String DUPLICATE_ERROR_MESSAGE = "자동차 이름은 중복될 수 없습니다.";

    private final List<Name> names;

    private Names(List<Name> names) {
        validateNames(names);
        this.names = List.copyOf(names);
    }

    public static Names from(String input) {
        validateEmptyInput(input);
        List<Name> names = splitNames(input);
        return new Names(names);
    }

    private static List<Name> splitNames(String input) {
        return Stream.of(input.split(SEPARATOR, -1))
                .map(Name::from)
                .collect(Collectors.toList());
    }

    private static void validateEmptyInput(String input) {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException(EMPTY_INPUT_ERROR_MESSAGE);
        }
    }

    private void validateNames(List<Name> names) {
        validateAtLeastTwo(names);
        validateNoDuplicates(names);
    }

    private void validateAtLeastTwo(List<Name> names) {
        if (names.size() < 2) {
            throw new IllegalArgumentException(TOO_FEW_CARS_MESSAGE);
        }
    }

    private void validateNoDuplicates(List<Name> names) {
        Set<String> distinct = names.stream()
                .map(Name::name)
                .collect(Collectors.toSet());
        if (distinct.size() != names.size()) {
            throw new IllegalArgumentException(DUPLICATE_ERROR_MESSAGE);
        }
    }

    public List<Name> asList() {
        return names;
    }

}
