import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;

public class Advent10 {

    private static long check(LinkedList<Integer> joltAdapters) {
        joltAdapters.sort(new SortByValue());
        joltAdapters.addFirst(0);
        Integer[] attempt = joltAdapters.toArray(new Integer[0]);

        long count = 1;
        int localCount = 1;
        short stroke = 0;
        int diff = attempt[1];

        for (int i = 1; i < attempt.length; i++) {
            if (diff == 1) {
                stroke++;
            } else if (diff == 3) {
                count = countStroke(count, localCount, stroke);
                stroke = 0;
                localCount = 1;
            } else {
                System.out.println("Problem : diff = " + diff);
            }
            if (i < attempt.length - 1) {
                diff = attempt[i + 1] - attempt[i];
            }
        }
        return countStroke(count, localCount, stroke);
    }

    private static long countStroke(long count, int localCount, short stroke) {
        stroke--;
        if (stroke > 0) {
            localCount = (int) Math.pow(2, stroke);
            if (stroke > 2) {
                localCount--;
            }
        }
        count *= localCount;
        return count;
    }

    public static long parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        LinkedList<Integer> joltAdaptors = new LinkedList<>();
        while (line != null) {
            joltAdaptors.add(Integer.parseInt(line));
            line = bufferedReader.readLine();
        }
        return check(joltAdaptors);
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input10.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            long count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class SortByValue implements Comparator<Integer> {
        @Override
        public int compare(Integer i, Integer j) {
            return i - j;
        }
    }
}

