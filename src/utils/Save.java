package utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;


public class Save {
    public static final String SEPARATOR = System.getProperty("file.separator");
    public static final String PATH = System.getProperty("user.dir") + SEPARATOR + "src" + SEPARATOR + "data" + SEPARATOR;
    
    public static void saveScore(String puntaje) throws IOException {
        File file = new File(PATH + "score.txt");
        
        if(!file.exists()) {
            file.createNewFile();
        }
        
        FileWriter fileWriter = new FileWriter(file);
        try (BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
            bufferedWriter.write(puntaje);
        }
    }
}
