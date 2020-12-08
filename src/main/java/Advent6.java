import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Advent6 {


    static int count = 0;

    private static void add(String input, PriorityQueue<String> letters, boolean firstLine) {
        System.out.println(input);

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

        System.out.println(letters.toString());
//        count += letters.size();
    }


    public static void parse(BufferedReader bufferedReader) throws IOException {
        PriorityQueue<String> letters = new PriorityQueue<>(Comparator.naturalOrder());

        String line = bufferedReader.readLine();
        boolean newLine = true;

        while (line != null) {
            if (line.equals("")) {
                count += letters.size();
                letters = new PriorityQueue<>();
                newLine = true;
                System.out.println("\n");
            } else {
                add(line, letters, newLine);
                newLine = false;
            }
            line = bufferedReader.readLine();
        }
        count += letters.size();
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input6.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                parse(bufferedReader);
                System.out.println("Solution is " + count);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

