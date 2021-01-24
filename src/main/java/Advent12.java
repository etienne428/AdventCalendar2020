import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class Advent12 {

    enum Direction {
        North('N'),
        East('E'),
        South('S'),
        West('W'),
        Link('L'),
        Right('R'),
        Forward('F');
        char character;
        Direction(char c) {
            character = c;
        }
        static Direction getDirection(char c) {
            switch (c) {
                case 'N':
                    return North;
                case 'E':
                    return East;
                case 'S':
                    return South;
                case 'W':
                    return West;
                case 'L':
                    return Link;
                case 'R':
                    return Right;
                case 'F':
                    return Forward;
                default:
                    return null;
            }
        }
    }
    private static int north = 0;
    private static int east = 0;
    private static Direction dir = Direction.East;

    public static void check(LinkedList<String> order) {
        for (String o: order) {
            move(o);
//            System.out.println("After " + o + " : north = " + north
//                    + ", east = " + east + ", dir = " + dir.character);
        }
    }

    private static void move(String o) {
        char c = o.charAt(0);
        Direction newDir = Direction.getDirection(c);
        int card = Integer.parseInt(o.substring(1));
        addMove(newDir, card);
    }

    private static void addMove(Direction newDir, int card) {
        switch (newDir) {
            case North:
                north += card;
                break;
            case East:
                east += card;
                break;
            case South:
                north -= card;
                break;
            case West:
                east -= card;
                break;
            case Link:
                int linkDir = (dir.ordinal() - (card / 90) + 4) % 4;
                System.out.println("Dir = " + dir + " car = " + card);
                System.out.println((dir.ordinal() - (card / 90))
                        + " and " + (dir.ordinal() - (card / 90) + 4) % 4);
                dir = Direction.values()[linkDir];
                break;
            case Right:
                int rightDir = (dir.ordinal() + (card / 90)) % 4;
                dir = Direction.values()[rightDir];
                break;
            case Forward:
                addMove(dir, card);
                break;
        }
    }

    public static long parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        LinkedList<String> order = new LinkedList<>();
        while (line != null) {
            order.add(line);
            line = bufferedReader.readLine();
        }
        check(order);
        return Math.abs(north) + Math.abs(east);
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input12.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());

            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            long count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}