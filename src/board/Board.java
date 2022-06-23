package board;

import boat.Boat;
import components.Const;
import coordinate.Coordinate;
import java.util.ArrayList;

public class Board {

    public static final int SIZE = Const.BOART_SIZE;

    private final TypeBox[][] board;
    private final ArrayList<Boat> boats;

    public Board() {
        this.board = initBoard(SIZE);
        this.boats = new ArrayList<>();
    }

    private TypeBox[][] initBoard(int size) {
        TypeBox[][] boardAux = new TypeBox[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                boardAux[i][j] = TypeBox.UNKNOWN;
            }
        }

        return boardAux;
    }
    
    public void clearBoard() {
        boats.clear();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = TypeBox.UNKNOWN;
            }
        }
    }
    
    public boolean isDead() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if(board[i][j].equals(TypeBox.BOAT)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public boolean isDone() {
        return boats.size() == Const.QUANTITY_BOATS_TYPE * 4;
    }

    public boolean addBoat(Boat boat) {
        if (boat == null) {
            return false;
        }

        if (isValid(boat)) {
            boats.add(boat);

            for (Coordinate part : boat.getParts()) {
                board[part.getX()][part.getY()] = TypeBox.BOAT;
            }
            return true;
        }
        return false;
    }

    public boolean isValid(Boat boat) {
        Coordinate head = boat.getHead();
        Coordinate tail = boat.getTail();
        
        if ((head.getX() < 0 || head.getX() >= Const.BOART_SIZE) || (head.getY() < 0 || head.getY() >= Const.BOART_SIZE)) {
            return false;
        }

        if ((tail.getX() < 0 || tail.getX() >= Const.BOART_SIZE) || (tail.getY() < 0 || tail.getY() >= Const.BOART_SIZE)) {
            return false;
        }

        for (Coordinate part : boat.getParts()) {
            if (board[part.getX()][part.getY()].equals(TypeBox.BOAT)) {
                return false;
            }
        }

        return true;
    }

    public boolean evaluateShot(Coordinate shot) {
        if(board[shot.getX()][shot.getY()].equals(TypeBox.BOAT_TOUCHED)) {
            return true;
        }
        
        board[shot.getX()][shot.getY()] = TypeBox.WATER;

        for (Boat boat : boats) {
            if (boat.checkShot(shot)) {
                board[shot.getX()][shot.getY()] = TypeBox.BOAT_TOUCHED;
                return true;
            }
        }

        return false;
    }

    public TypeBox getTypeCoordinate(int i, int j) {
        return board[i][j];
    }
}
