import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Advent11 {
    private static char[] room;
    private static int columns;
    private static int[] neighboursLocation;

    private static int check(LinkedList<String> seats) {
        columns = seats.get(0).length();
        int rows = seats.size();
        neighboursLocation = new int[]{
                - columns - 1,   - columns,  - columns + 1,
                - 1,                         1,
                columns - 1,     columns,    columns + 1
        };

        room = new char[columns * rows];
        int i = 0;
        for (String s: seats) {
            for (char c: s.toCharArray()) {
                room[i++] = c;
            }
        }

        while (computeRound()) {
//            print(room);
        }
        print(room);
        return occupiedSeats();
    }

    private static void print(char[] room) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < room.length; i++) {
            if (i % columns == 0) {
                sb.append("\n");
            }
            sb.append(room[i]);
        }
        System.out.println(sb);
    }

    private static void print(int[] room) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < room.length; i++) {
            if (i % columns == 0) {
                sb.append("\n");
            }
            sb.append(room[i]);
        }
        System.out.println(sb);
    }


    private static boolean computeRound() {
        int[] neighbours = new int[room.length];
        boolean changed = false;
        for (int i = 0; i < room.length; i++) {
            if (room[i] != '.')
                neighbours[i] = numberOfNeighbours(i);
        }
        for (int i = 0; i < room.length; i++) {
            if (room[i] == 'L' && neighbours[i] == 0) {
                room[i] = '#';
                changed = true;
            } else if (room[i] == '#' && neighbours[i] >= 5) {
                room[i] = 'L';
                changed = true;
            }
        }
//        print(neighbours);
        return changed;
    }

    private static int numberOfNeighbours(int index) {
        int count = 0;
        int seatNumber;
        for (int i = 0; i < neighboursLocation.length; i++) {
            seatNumber = neighboursLocation[i] + index;
            try {
                while (true) {
                    if (index % columns == columns - 1
                            && (i == 2 || i == 4 || i == 7)) {
                        throw new IndexOutOfBoundsException();
                    } else if (index % columns == 0
                            && (i == 0 || i == 3 || i == 5)) {
                        throw new IndexOutOfBoundsException();
                    }
                    if (room[seatNumber] == '#') {
                        count++;
                        break;
                    } else if (room[seatNumber] == 'L') {
                        break;
                    }
                    seatNumber += neighboursLocation[i];
                }
            } catch (IndexOutOfBoundsException ignored) {
            }
        }
        return count;
    }

    private static int occupiedSeats() {
        int count = 0;
        for (char c: room) {
            if (c == '#') {
                count++;
            }
        }
        return count;
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
