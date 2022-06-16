package battleship;

import board.Board;
import player.*;

public class BattleShip {

    private Player player;
    private final Machine machine;

    private final Board boardPlayer;
    private final Board boardMachine;

    public BattleShip() {
        this.player = null;
        this.machine = new Machine();
        this.boardPlayer = new Board();
        this.boardMachine = new Board();
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    public Machine getMachine() {
        return machine;
    }

    public Board getBoardPlayer() {
        return boardPlayer;
    }

    public Board getBoardMachine() {
        return boardMachine;
    }

}
