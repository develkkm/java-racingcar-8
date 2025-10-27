package racingcar.model.game.car;

import racingcar.model.game.name.Name;

public class Car {
    private final Name name;
    private int distance = 0;

    private Car(Name name) {
        this.name = name;
    }

    public static Car from(Name name) {
        return new Car(name);
    }

    public void moveForward() {
        distance++;
    }

    public String name() {
        return name.name();
    }

    public int distance() {
        return distance;
    }

}
