package racingcar.controller;

import racingcar.domain.Car;
import racingcar.domain.Cars;
import racingcar.view.InputView;
import racingcar.view.OutputView;
import racingcar.vo.CarName;
import racingcar.vo.Trial;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller {
    public static final String DUPLICATING_NAME_EXCEPTION_MESSAGE = "중복된 이름은 사용할 수 없습니다.";

    private final OutputView outputView;
    private final InputView inputView;
    private final Cars cars;

    public Controller(OutputView outputView, InputView inputView, Cars cars) {
        this.outputView = outputView;
        this.inputView = inputView;
        this.cars = cars;
    }

    public void run() {
        setGame();
        playGame();
        printFinalResult();
    }

    private void setGame() {
        initializeCars();
    }

    private void playGame() {
        Trial trial = getTrial();
        outputView.printResultMessage();
        for (int count = 0; count < trial.getValue(); count++) {
            cars.move();
            printResult();
        }
    }

    private Trial getTrial() {
        try {
            return inputView.getTrial();
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            return getTrial();
        }
    }

    private void initializeCars() {
        try {
            List<CarName> carNames = inputView.getCarNames();
            validateDuplication(carNames);
            saveCars(carNames);
        } catch (IllegalArgumentException exception) {
            outputView.printErrorMessage(exception.getMessage());
            initializeCars();
        }
    }

    private void saveCars(List<CarName> carNames) {
        for (CarName carName : carNames) {
            cars.saveCar(Car.of(carName));
        }
    }

    private void validateDuplication(List<CarName> names) {
        Set<CarName> namesWithoutDuplication = new HashSet<>(names);
        if (names.size() != namesWithoutDuplication.size()) {
            throw new IllegalArgumentException(DUPLICATING_NAME_EXCEPTION_MESSAGE);
        }
    }

    private void printFinalResult() {
        printResult();
        outputView.printWinners(cars.getWinnerNames());
    }

    private void printResult() {
        outputView.printResult(cars.getResult());
    }
}
