package player;

import boat.AircraftCarrier;
import boat.Boat;
import boat.Direction;
import boat.Ironcland;
import boat.Submarine;
import boat.Wrecker;
import components.Const;
import coordinate.Coordinate;
import java.util.Random;

public class Machine extends Player {
    
    private final Random random;

    public Machine() {
        super("Crocodrile", 0);
        random = new Random();
    }

    public Coordinate attack() {
        return new Coordinate(random.nextInt(Const.BOART_SIZE), random.nextInt(Const.BOART_SIZE));
    }

    public void buildingBoard(board.Board board) {
        Boat boat;
        int countBoats[] = new int[]{0, 0, 0, 0};
        int index = 0;

        while (!board.isDone()) {
            if (countBoats[index] < Const.QUANTITY_BOATS_TYPE) {
                switch (Const.TYPES_BOATS[index]) {
                    case AIRCRAFT_CARRIER:
                        boat = new AircraftCarrier(random.nextInt(10) % 2 == 0 ? Direction.VERTICAL : Direction.HORIZONTAL, new Coordinate(random.nextInt(Const.BOART_SIZE), random.nextInt(Const.BOART_SIZE)));
                        break;
                    case IRONCLAD:
                        boat = new Ironcland(random.nextInt(10) % 2 == 0 ? Direction.VERTICAL : Direction.HORIZONTAL, new Coordinate(random.nextInt(Const.BOART_SIZE), random.nextInt(Const.BOART_SIZE)));
                        break;
                    case SUBMARINE:
                        boat = new Submarine(random.nextInt(10) % 2 == 0 ? Direction.VERTICAL : Direction.HORIZONTAL, new Coordinate(random.nextInt(Const.BOART_SIZE), random.nextInt(Const.BOART_SIZE)));
                        break;
                    default:
                        boat = new Wrecker(random.nextInt(10) % 2 == 0 ? Direction.VERTICAL : Direction.HORIZONTAL, new Coordinate(random.nextInt(Const.BOART_SIZE), random.nextInt(Const.BOART_SIZE)));
                        break;
                }

                if (board.addBoat(boat)) {
                    countBoats[index]++;
                }
            } else {
                index++;
            }
        }
    }
}
