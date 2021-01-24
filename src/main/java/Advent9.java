import java.io.*;
import java.util.LinkedList;

public class Advent9 {
    private static final LinkedList<Long> codes = new LinkedList<>();
    private static int preambleLength;

    private static long check() {
        for (int i = preambleLength; i < codes.size(); i++) {
            if (getWrongNumber(i)) {
                return getSet(i);
            }
        }
        return -1;
    }

    private static long getSet(int index) {
        long sum;
        for (int low = 0; low < codes.size(); low++) {
            sum = 0;
            for (int i = low; i < codes.size(); i++) {
                sum += codes.get(i);
                if (sum == codes.get(index)) {
                    return findBounds(low, i);
                }
            }

        }
        return 0;
    }

    private static long findBounds(int low, int high) {
        long min = codes.get(high);
        long max = codes.get(high);
        long tmp;
        for (int i = low; i < high; i++) {
            tmp = codes.get(i);
            if (min < tmp) {
                min = tmp;
            } else if (tmp < max) {
                max = tmp;
            }
        }
        return min + max;
    }

    private static boolean getWrongNumber(int index) {
        for (int i = index - preambleLength; i < index; i++) {
            for (int j = i + 1; j <= index; j++) {
                if (codes.get(i) + codes.get(j) == codes.get(index)) {
                    return false;
                }
            }
        }
        return true;
    }

    public static long parse(BufferedReader bufferedReader, int preambleLength) throws IOException {
        Advent9.preambleLength = preambleLength;
        String line = bufferedReader.readLine();
        while (line != null) {
            codes.add(Long.parseLong(line));
            line = bufferedReader.readLine();
        }
        return check();
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input9.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            long count = parse(bufferedReader, 25);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}

