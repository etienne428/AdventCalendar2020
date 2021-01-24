import java.io.*;

public class Advent5 {


    private static final boolean[] flight = new boolean[890];


    private static void check(String input) {
        String rowString = input.substring(0, 7);
        String seatString = input.substring(7);

        int row = findRow(rowString, new int[]{0, 127});
        int seat = findSeat(seatString, new int[]{0, 7});

        System.out.println("\n" + row + " " + seat);
        flight[row * 8 + seat] = true;
    }

    private static int findSeat(String seatString, int[] lim) {
        if (seatString.equals("")) {
            return lim[0];
        } else {
            if (seatString.charAt(0) == 'L') {
                lim[1] -= (lim[1] - lim[0])  / 2;
                lim[1]--;
                return findSeat(seatString.substring(1), lim);
            } else if (seatString.charAt(0) == 'R') {
                lim[0] += (lim[1] - lim[0])  / 2;
                lim[0]++;
                return findSeat(seatString.substring(1), lim);
            }
        }
        return -1;
    }

    private static int findRow(String rowString, int[] lim) {
        if (rowString.equals("")) {
            return lim[0];
        } else {
            if (rowString.charAt(0) == 'F') {
                lim[1] -= (lim[1] - lim[0])  / 2;
                lim[1]--;
                return findRow(rowString.substring(1), lim);
            } else if (rowString.charAt(0) == 'B') {
                lim[0] += (lim[1] - lim[0])  / 2;
                lim[0]++;
                return findRow(rowString.substring(1), lim);
            }
        }
        return -1;
    }

    public static void parse(BufferedReader bufferedReader) throws IOException {

        String line = bufferedReader.readLine();
        while (line != null) {

            check(line);
            line = bufferedReader.readLine();
        }
        printFlight();
    }

    private static void printFlight() {
        int i = 0;
        StringBuilder b = new StringBuilder();
        while (i < flight.length) {
            if (flight[i]) {
                System.out.print("X ");
            } else {
                b.append(i).append(" ");
                System.out.print("0 ");
            }
            i++;
            if (i % 8 == 0) {
                System.out.println();
            }
        }
        System.out.println("\n" + b.toString());
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input5.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            parse(bufferedReader);
            int maxSeat = 0;
            System.out.println("Solution is " + maxSeat);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}