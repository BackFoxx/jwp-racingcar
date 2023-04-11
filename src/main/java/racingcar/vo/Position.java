package racingcar.vo;

import java.util.Objects;

public class Position {
    private final Long position;

    private Position(Long position) {
        this.position = position;
    }

    public static Position of(Long position) {
        return new Position(position);
    }

    public Position plus() {
        return Position.of(position + 1);
    }

    public Long getValue() {
        return position;
    }

    public boolean isValueOf(Long position) {
        return Objects.equals(this.position, position);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position1 = (Position) o;
        return position.equals(position1.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(position);
    }

    @Override
    public String toString() {
        return "Position{" +
                "position=" + position +
                '}';
    }
}
