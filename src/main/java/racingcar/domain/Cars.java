package racingcar.domain;

import racingcar.dto.Result;
import racingcar.utils.NumberGenerator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Cars {
    private final List<Car> cars;
    private final NumberGenerator numberGenerator;

    public Cars(List<Car> cars, NumberGenerator numberGenerator) {
        this.cars = cars;
        this.numberGenerator = numberGenerator;
    }

    public void saveCar(Car car) {
        cars.add(car);
    }

    public void move() {
        cars.forEach((car) -> car.move(numberGenerator.generateNumber()));
    }

    public List<String> getWinnerNames() {
        Long highestPosition = calculateHighestPosition();
        return cars.stream()
                .filter(car -> hasHighestPosition(highestPosition, car))
                .map(Car::getName)
                .collect(Collectors.toUnmodifiableList());
    }

    public Result getResult() {
        return Result.of(cars);
    }

    private static boolean hasHighestPosition(Long highestPosition, Car car) {
        return car.hasPosition(highestPosition);
    }

    private Long calculateHighestPosition() {
        return Collections.max(cars.stream()
                .map((Car::getPosition))
                .collect(Collectors.toUnmodifiableList()));
    }

    @Override
    public String toString() {
        return "Cars{" +
                "cars=" + cars +
                ", randomNumberGenerator=" + numberGenerator +
                '}';
    }
}
