import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;

public class Advent8 {

    private static int acc = 0;
    private static final LinkedList<String> commands = new LinkedList<>();
    private static boolean[] visited;
    private static int modifiedLine = -1;

    private static void check() {
        visited = new boolean[commands.size()];
        do {
            Arrays.fill(visited, false);
            acc = 0;
            modifyData();
        } while (!executeCommand(0));
    }

    private static void modifyData() {
        if (modifiedLine != -1) {
            switchLine();
        }
        do {
            modifiedLine++;
        } while (commands.get(modifiedLine).startsWith("acc"));
        switchLine();
    }

    private static void switchLine() {
        String line = commands.get(modifiedLine);
        if (line.startsWith("nop")) {
            line = "jmp" + line.substring(3);
        } else {
            assert(line.startsWith("jmp"));
            line = "nop" + line.substring(3);
        }
        commands.set(modifiedLine, line);
    }

    private static boolean executeCommand(int index) {
        if (index >= visited.length) {
            return true;
        } else if (!visited[index]) {
            visited[index] = true;
        } else {
            return false;
        }
        String cmd = commands.get(index);
        if (cmd.startsWith("nop")) {
            return executeCommand(++index);
        } else if (cmd.startsWith("acc")) {
            int number = Integer.parseInt(cmd.substring(5));
            if (cmd.charAt(4) == '+') {
                acc += number;
            } else {
                assert(cmd.charAt(4) == '-');
                acc -= number;
            }
            return executeCommand(++index);
        } else {
            assert(cmd.startsWith("jmp"));
            int number = Integer.parseInt(cmd.substring(5));
            if (cmd.charAt(4) == '+') {
                return executeCommand(index + number);
            } else {
                assert(cmd.charAt(4) == '-');
                return executeCommand(index - number);
            }
        }
    }

    public static int parse(BufferedReader bufferedReader) throws IOException {

        String line = bufferedReader.readLine();
        while (line != null) {
            commands.add(line);
            line = bufferedReader.readLine();
        }
        check();
        return acc;
    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input8.txt");
        try {
            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());


            BufferedReader bufferedReader;
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                int count = parse(bufferedReader);

                System.out.println("Solution is " + count);
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
