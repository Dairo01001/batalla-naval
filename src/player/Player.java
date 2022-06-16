package player;

public class Player {

    private final String nombre;
    private int score;

    public Player(String nombre, int score) {
        this.nombre = nombre;
        this.score = score;
    }

    public String getNombre() {
        return nombre;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Player [" + "nombre: " + nombre + ", score: " + score + ']';
    }
}
