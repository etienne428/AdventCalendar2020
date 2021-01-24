import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Advent6 {


    static int count = 0;

    private static void add(String input, PriorityQueue<String> letters, boolean firstLine) {

        if (firstLine) {
            for (int i = 0; i < input.length(); i++) {
                letters.add(input.substring(i, i + 1));
            }
        } else {
            PriorityQueue<String> newLetters = new PriorityQueue<>(Comparator.naturalOrder());
            for (int i = 0; i < input.length(); i++) {
                newLetters.add(input.substring(i, i + 1));
            }
            letters.removeIf(s -> !newLetters.contains(s));
        }

    }


    public static int parse(BufferedReader bufferedReader) throws IOException {
        PriorityQueue<String> letters = new PriorityQueue<>(Comparator.naturalOrder());

        String line = bufferedReader.readLine();
        boolean newLine = true;

        while (line != null) {
            if (line.equals("")) {
                count += letters.size();
                letters = new PriorityQueue<>();
                newLine = true;
            } else {
                add(line, letters, newLine);
                newLine = false;
            }
            line = bufferedReader.readLine();
        }
        count += letters.size();
        return count;
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input6.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

