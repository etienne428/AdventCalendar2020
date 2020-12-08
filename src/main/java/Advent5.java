import java.io.*;
import java.util.Arrays;

public class Advent5 {


    private static boolean[] flight = new boolean[890];
    private static int maxSeat = 0;


    private static void check(String input) {
        String rowString = input.substring(0, 7);
        String seatString = input.substring(7);

//        System.out.println(input);
        int row = findRow(rowString, new int[]{0, 127});
//        System.out.println("I'm here!\n\n");
        int seat = findSeat(seatString, new int[]{0, 7});

        System.out.println("\n" + row + " " + seat);
        flight[row * 8 + seat] = true;
    }

    private static int findSeat(String seatString, int[] lim) {
        if (seatString.equals("")) {
//            System.out.println(lim[0] + " == " + lim[1]);
            return lim[0];
        } else {
//            System.out.println(seatString.charAt(0) + " " + seatString.substring(1));
            if (seatString.charAt(0) == 'L') {
                lim[1] -= (lim[1] - lim[0])  / 2;
                lim[1]--;
//                System.out.println(lim[0] + " == " + lim[1]);
                return findSeat(seatString.substring(1), lim);
            } else if (seatString.charAt(0) == 'R') {
                lim[0] += (lim[1] - lim[0])  / 2;
                lim[0]++;
//                System.out.println(lim[0] + " == " + lim[1]);
                return findSeat(seatString.substring(1), lim);
            }
        }
        return -1;
    }

    private static int findRow(String rowString, int[] lim) {
//        System.out.println(rowString + " = rowString");
        if (rowString.equals("")) {
//            System.out.println(lim[0] + " == " + lim[1]);
            return lim[0];
        } else {
//            System.out.println(rowString.charAt(0) + " " + rowString.substring(1) + " " + (rowString.charAt(0) == 'F'));
            if (rowString.charAt(0) == 'F') {
                lim[1] -= (lim[1] - lim[0])  / 2;
                lim[1]--;
//                System.out.println(lim[0] + " =F= " + --lim[1]);
                return findRow(rowString.substring(1), lim);
            } else if (rowString.charAt(0) == 'B') {
                lim[0] += (lim[1] - lim[0])  / 2;
                lim[0]++;
//                System.out.println(++lim[0] + " =B= " + lim[1]);
                return findRow(rowString.substring(1), lim);
            }
        }
        return -1;
    }

    public static void parse(BufferedReader bufferedReader) throws IOException {

        String line = bufferedReader.readLine();
        int i;
        for (i = 0; line != null; i++) {

            check(line);
            line = bufferedReader.readLine();
        }
        printFlight();
    }

    private static void printFlight() {
        int i = 0;
        StringBuffer b = new StringBuffer();
        while (i < flight.length) {
            if (flight[i]) {
                System.out.print("X ");
            } else {
                b.append(i + " ");
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
        File file = new File("src\\main\\resources\\input5.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                parse(bufferedReader);

                System.out.println("Solution is " + maxSeat);
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