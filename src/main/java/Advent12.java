import java.io.BufferedReader;
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

        static Direction getOpposite(Direction c) {
            switch (c) {
                case North:
                    return South;
                case East:
                    return West;
                case South:
                    return North;
                case West:
                    return East;
                default:
                    return null;
            }
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
    private static final int[] waypoint = {1, 10};
    private static final Direction[] waypointDir = {Direction.North, Direction.East};

    public static void check(LinkedList<String> order) {
        for (String o : order) {
            char c = o.charAt(0);
            Direction newDir = Direction.getDirection(c);
            int card = Integer.parseInt(o.substring(1));
            assert newDir != null;
            addMove(newDir, card);
            System.out.println("\nAfter " + o + " : north = " + north
                    + ", east = " + east);
            System.out.println("wayPoint    " + waypoint[0] + " " + waypoint[1]);
            System.out.println("wayPointDir " + waypointDir[0] + " " + waypointDir[1]);
        }
    }

    private static void changeWayPoint(Direction direction, int card) {
        if (waypointDir[0] == direction) {
            waypoint[0] += card;
        } else if (waypointDir[0] == Direction.getOpposite(direction)) {
            waypoint[0] -= card;
        } else if (waypointDir[1] == direction) {
            waypoint[1] += card;
        } else if (waypointDir[1] == Direction.getOpposite(direction)) {
            waypoint[1] -= card;
        }
    }

    private static void rotate(int card, boolean minus) {
        int wp0;
        int wp1;
        if (minus) {
            wp0 = (waypointDir[0].ordinal() - (card / 90) + 4) % 4;
            wp1 = (waypointDir[1].ordinal() - (card / 90) + 4) % 4;
        } else {
            wp0 = (waypointDir[0].ordinal() + (card / 90) + 4) % 4;
            wp1 = (waypointDir[1].ordinal() + (card / 90) + 4) % 4;
        }
            waypointDir[0] = Direction.values()[wp0];
            waypointDir[1] = Direction.values()[wp1];
    }

    private static void doMove(int card) {
        for (int i = 0; i < 2; i++) {
            switch (waypointDir[i]) {
                case North:
                    north += card * waypoint[i];
                    break;
                case East:
                    east += card * waypoint[i];
                    break;
                case South:
                    north -= card * waypoint[i];
                    break;
                case West:
                    east -= card * waypoint[i];
                    break;
                default:
                    System.out.println("Error : " + waypointDir[i].name());
            }
        }
    }

    private static void addMove(Direction newDir, int card) {
        switch (newDir) {
            case North:
            case East:
            case South:
            case West:
                changeWayPoint(newDir, card);
                break;
            case Link:
                rotate(card, true);
                break;
            case Right:
                rotate(card, false);
                break;
            case Forward:
                doMove(card);
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
        String fileName = "src\\main\\resources\\input12.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            long count = parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}