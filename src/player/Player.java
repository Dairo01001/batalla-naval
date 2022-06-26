package player;

public class Player {

    private String nombre;
    private int score;
    
    public Player() {
        
    }

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
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Player [" + "nombre: " + nombre + ", score: " + score + ']';
    }
}
