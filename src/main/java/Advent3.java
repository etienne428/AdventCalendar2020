import java.io.*;
import java.util.Arrays;

public class Advent3 {

    private static final int[] count = new int[5];


    private static void check(String input, int line) {
        int[] jumps = {1, 3, 5, 7, 1};
        for (int j = 0; j < jumps.length; j++) {
            if (j != 4) {
                if (input.charAt((jumps[j] * line) % input.length()) == '#') {
                    count[j]++;
                }
            } else if (line % 2 == 0 && input.charAt((jumps[j] * line / 2) % input.length()) == '#') {
                count[j]++;
            }
        }

    }

    private static long computeSolution() {
        long i = 1;
        for (int j: count) {
            i *= j;
        }
        return i;
    }

    public static double parse(BufferedReader bufferedReader) throws IOException {

        String line = bufferedReader.readLine();
        int i;
        for (i = 0; line != null; i++) {

            check(line, i);
            line = bufferedReader.readLine();
        }
        return computeSolution();
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input3.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                parse(bufferedReader);
                System.out.println("Solution is " + Arrays.toString(count));

                System.out.println("Solution is " + computeSolution());
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
