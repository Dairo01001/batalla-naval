package boat;

import coordinate.Coordinate;

public class Wrecker extends Boat {

    public static final int SIZE = 2;

    public Wrecker(Direction direction, Coordinate coordinate) {
        super(direction, SIZE, coordinate);

    }

    @Override
    public String toString() {
        return "Wrecker [" + super.toString() + ", SIZE: " + SIZE + ']';
    }
}
