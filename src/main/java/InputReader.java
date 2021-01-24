import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class InputReader {
    public static BufferedReader read(String fileString) throws IOException {
        File file = new File(fileString);
        System.out.println("Attempting to read from file in: " + file.getCanonicalPath());
        return new BufferedReader(new FileReader(file));
    }
}
