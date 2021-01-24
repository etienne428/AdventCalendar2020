import java.io.*;
import java.util.Arrays;

public class Advent2 {

    public static int parse(BufferedReader bufferedReader) throws IOException {
        String[] input;
        int count = 0;

        String line = bufferedReader.readLine();
        while (line != null) {
            input = line.split(" ", 3);

            System.out.println(Arrays.toString(input));
            if (check(input)) {
                System.out.println("YEAH!!!!!!!!!!!!!!");
                count++;
            } else {
                System.out.println("OHHHHHHHHHHHHHHHH...................");
            }

            line = bufferedReader.readLine();

        }

        return count;
    }

    private static boolean check(String[] input) {
        String[] minMax = input[0].split("-", 2);
        int min = Integer.parseInt(minMax[0]);
        int max = Integer.parseInt(minMax[1]);

        char letter = input[1].charAt(0);

        if (input[2].charAt(min - 1) == letter) {
            return input[2].charAt(max - 1) != letter;
        }
        return input[2].charAt(max - 1) == letter;
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input2.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            int count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}