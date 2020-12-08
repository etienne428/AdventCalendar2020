import java.io.*;
import java.util.Arrays;

public class Advent2 {

    public static int parse(BufferedReader bufferedReader) throws IOException {
        int length = 200;
        String[] input = new String[3];
        int count = 0;

        String line = bufferedReader.readLine();
        for (int i = 0; line != null; i++) {
            input = line.split(" ", 3);

            System.out.println(Arrays.toString(input));
            if (check(input)) {
                System.out.println("YEAH!!!!!!!!!!!!!!");
                count++;
            } else {
                System.out.println("OHHHHHHHHHHHHHHHH...................");
            }

            line = bufferedReader.readLine();
//            System.out.println(in[i]);

        }

        return count;
    }

    private static boolean check(String[] input) {
//        System.out.println("min in char = " + input[0].split("-")[0]);
        String[] minMax = input[0].split("-", 2);
//        System.out.println(minMax[0] + " = minMax");
        int min = (int) Integer.parseInt(minMax[0]);
//        System.out.println("min in int = " + min);
        int max = Integer.parseInt(minMax[1]);

        char letter = input[1].charAt(0);

        if (input[2].charAt(min - 1) == letter) {
            return input[2].charAt(max - 1) != letter;
        }
        return input[2].charAt(max - 1) == letter;
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input2.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            int amount = parse(bufferedReader);
            System.out.println("Solution is " + amount);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}