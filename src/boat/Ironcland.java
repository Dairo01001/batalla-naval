package boat;

import coordinate.Coordinate;

public class Ironcland extends Boat {

    public static final int SIZE = 4;

    public Ironcland(Direction direction, Coordinate coordinate) {
        super(direction, SIZE, coordinate);

    }

    @Override
    public String toString() {
        return "Ironcland [" + super.toString() + ", SIZE: " + SIZE + ']';
    }

}
