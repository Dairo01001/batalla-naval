package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Open {

    public static String openScore() throws FileNotFoundException {
        File doc = new File(Save.PATH + "score.txt");

        StringBuilder stringBuilder = new StringBuilder();

        Scanner scanner = new Scanner(doc);

        stringBuilder.append(String.format("%12s %4s %4s\n", "Nombre", "Puntaje", "Error"));
        while (scanner.hasNextLine()) {
            String[] params = scanner.nextLine().split(",");
            stringBuilder.append(String.format("%12s %4s %6s\n", params[0], params[1], params[2]));
        }

        return stringBuilder.toString();
    }
}
