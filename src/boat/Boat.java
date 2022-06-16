package boat;

import coordinate.Coordinate;
import java.util.Arrays;

public class Boat {

    private final Direction direction;
    private final Coordinate[] parts;
    private int lifes;

    public Boat(Direction direction, int size, Coordinate coordinate) {
        this.direction = direction;
        this.parts = new Coordinate[size];
        this.lifes = size;

        spawnShip(coordinate);
    }

    public Direction getDirection() {
        return direction;
    }

    public Coordinate[] getParts() {
        return parts;
    }

    public int getLifes() {
        return lifes;
    }

    public Coordinate getTail() {
        return parts[parts.length - 1];
    }
    
    public Coordinate getHead() {
        return parts[0];
    }

    private void spawnShip(Coordinate coordinate) {
        int posX = coordinate.getX();
        int posY = coordinate.getY();
        boolean dir = Direction.HORIZONTAL.equals(direction);

        for (int i = 0; i < parts.length; i++) {
            parts[i] = new Coordinate(posX, posY);
            if (dir) {
                posY++;
            } else {
                posX++;
            }
        }
    }

    public boolean checkShot(Coordinate shot) {
        for (Coordinate part : parts) {
            if (part.getX() == shot.getX() && part.getY() == shot.getY()) {
                lifes--;
                return true;
            }
        }
        return false;
    }

    public boolean checkSag() {
        return lifes == 0;
    }

    @Override
    public String toString() {
        return "Boat [" + "direction: " + direction + ", parts: " + Arrays.toString(parts) + ", life: " + lifes + ']';
    }
}
