import java.io.BufferedReader;
import java.io.IOException;
import java.util.LinkedList;

public class Advent13 {

    private static int startingTime;
    private static String[] buses;
    private static int waitingTime;
    private static int busToTake;

    private static void check() {
        int busID;
        waitingTime = startingTime;
        for (String bus: buses) {
            if (!bus.equals("x")) {
                busID = Integer.parseInt(bus);
//                System.out.println(Long.MAX_VALUE);
                System.out.println(busID + " wT " + (busID - (startingTime % busID)));
                if ((busID - (startingTime % busID)) < waitingTime) {
                    waitingTime = (busID - (startingTime % busID));
                    busToTake = busID;
                }
            }
        }
    }

    public static long parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        startingTime = Integer.parseInt(line);
        buses = bufferedReader.readLine().split(",");
        check();
        return (long) busToTake * waitingTime;
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input13.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            long count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

