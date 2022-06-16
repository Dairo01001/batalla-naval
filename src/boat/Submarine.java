package boat;

import coordinate.Coordinate;

public class Submarine extends Boat {

    public static final int SIZE = 3;

    public Submarine(Direction direction, Coordinate coordinate) {
        super(direction, SIZE, coordinate);

    }

    @Override
    public String toString() {
        return "Submarine [" + super.toString() + ", SIZE: " + SIZE + ']';
    }
}
