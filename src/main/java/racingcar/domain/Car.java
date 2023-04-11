package racingcar.domain;

import racingcar.vo.CarName;
import racingcar.vo.Position;

public class Car {
    public static final int MIN_REQUIRED_POWER = 3;

    private final CarName carName;
    private Position position;

    private Car(CarName carName) {
        this.carName = carName;
        this.position = Position.of(0L);
    }

    public static Car of(CarName name) {
        return new Car(name);
    }

    public String getName() {
        return carName.getValue();
    }

    public Long getPosition() {
        return position.getValue();
    }

    public void move(int power) {
        if (power > MIN_REQUIRED_POWER) {
            position = position.plus();
        }
    }

    public boolean hasPosition(Long position) {
        return this.position.isValueOf(position);
    }

    @Override
    public String toString() {
        return "Car{" +
                "name=" + carName +
                ", position=" + position +
                '}';
    }
}
