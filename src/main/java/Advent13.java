import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Advent13 {

    private static long check(LinkedList<String> seats) {
        return 0;
    }

    public static long parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        LinkedList<String> seats = new LinkedList<>();
        while (line != null) {
            seats.add(line);
            line = bufferedReader.readLine();
        }
        return check(seats);
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input11.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            long count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

