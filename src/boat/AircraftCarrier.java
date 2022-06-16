package boat;

import coordinate.Coordinate;

public class AircraftCarrier extends Boat {

    public static final int SIZE = 5;

    public AircraftCarrier(Direction direction, Coordinate coordinate) {
        super(direction, SIZE, coordinate);

    }

    @Override
    public String toString() {
        return "AircraftCarrier [" + super.toString() + ", SIZE: " + SIZE + ']';
    }
}
