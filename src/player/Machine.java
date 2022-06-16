package player;

import coordinate.Coordinate;

public class Machine extends Player {

    public Machine() {
        super("Crocodrile", 0);
    }

    public Coordinate attack() {
        return new Coordinate(0, 0);
    }
}
