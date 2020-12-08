import java.io.*;

public class Advent1 {

    public static int[] findNumbers(BufferedReader input) throws IOException {
        int length = 200;
        int[] in = new int[length];

        String line = input.readLine();
        for (int i = 0; line != null; i++) {
            in[i] = Integer.parseInt(line);
            line = input.readLine();
//            System.out.println(in[i]);
        }

        int[] output = new int[3];

        for (int i = 0; i < length; i++) {
            for (int j = 1; j < length; j++) {
                for (int k = 2; k < length; k++) {
                if (in[i] + in[j] + in[k] == 2020) {
                    System.out.println(k);
                    output[0] = in[i];
                    output[1] = in[j];
                    output[2] = in[k];
                    return output;
                }
                }
            }
        }
        return output;
    }

    public static void main(String[] args) {
        File file = new File("input.txt");
        BufferedReader input = null;
        try {
            input = new BufferedReader(new FileReader(file));
            int[] numbers = findNumbers(input);
            System.out.println(numbers[0] + " * " + numbers[1] + " * " + numbers[2] + " = " + numbers[0] * numbers[1] * numbers[2]);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}