package main;

import board.Board;
import coordinate.Coordinate;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelBoard extends JPanel {

    private Cell[][] cells;

    public PanelBoard() {
        init();
    }

    private void init() {
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        cells = new Cell[Board.SIZE][Board.SIZE];

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[0].length; j++) {
                cells[i][j] = new Cell(new Coordinate(i, j));
                add(cells[i][j]);
            }
        }

        setLayout(new GridLayout(Board.SIZE + 1, Board.SIZE + 1, 4, 4));
    }

    public void setModelBoard(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                switch (board.getTypeCoordinate(i, j)) {
                    case BOAT_TOUCHED:
                        cells[i][j].setBackground(Color.RED);
                        break;
                    case BOAT:
                        cells[i][j].setBackground(Color.GREEN);
                        break;
                    case UNKNOWN:
                        cells[i][j].setBackground(Color.GRAY);
                        break;
                    default:
                        cells[i][j].setBackground(Color.BLUE);
                        break;
                }

            }
        }
    }

    public void setModelForMachine(Board board) {
        for (int i = 0; i < Board.SIZE; i++) {
            for (int j = 0; j < Board.SIZE; j++) {
                switch (board.getTypeCoordinate(i, j)) {
                    case WATER:
                        cells[i][j].setBackground(Color.BLUE);
                        break;
                    case BOAT_TOUCHED:
                        cells[i][j].setBackground(Color.RED);
                        break;
                    default:
                        cells[i][j].setBackground(Color.GRAY);

                }
            }
        }
    }

    private class Cell extends JPanel {

        Coordinate coord;

        public Cell(Coordinate coord) {
            this.coord = coord;
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
            init();
        }

        public Cell(JLabel numCell) {
            setPreferredSize(new Dimension(25, 25));
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 4, true));
            add(numCell);
            setBackground(Color.ORANGE);
        }

        private void init() {
            setPreferredSize(new Dimension(50, 50));
            setBackground(Color.CYAN);
        }

    }
}
