package components;

import boat.TypeBoat;
import java.awt.Font;

public class Const {

    public static final Font FONT = new Font("Courier New", Font.PLAIN, 14);
    public static final int BOART_SIZE = 8;
    public static final int QUANTITY_BOATS_TYPE = 2;
    public static final String[] NAMES_BOATS = {"Porta Aviones", "Acorazado", "Submarino", "Destructor"};
    public static final TypeBoat[] TYPES_BOATS = {TypeBoat.AIRCRAFT_CARRIER, TypeBoat.IRONCLAD, TypeBoat.SUBMARINE, TypeBoat.WRECKER};
}
