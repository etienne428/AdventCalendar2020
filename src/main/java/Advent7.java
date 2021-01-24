import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

public class Advent7 {

    private static final LinkedList<Node> bagOfBags = new LinkedList<>();
    static int count = 0;

    private static void check(String input) {
        System.out.println(input);
        String bag = input.split(" bags contain")[0];
        String[] childBags  = input.split(" bags contain")[1].split(",");

        Node parentBag = getBag(bag);
        if (parentBag == null) {
            parentBag = new Node(bag);
            bagOfBags.add(parentBag);
        }
        if (!childBags[0].contains(" no ")) {
            for (int i = 0; i < childBags.length; i++) {
                childBags[i] = childBags[i].substring(3, childBags[i].indexOf(" bag"));
                Node child = getBag(childBags[i]);
                if (child == null) {
                    child = new Node(childBags[i]);
                }
                bagOfBags.add(child);
                child.addParent(parentBag);
            }
        }
    }

    private static Node getBag(String bag) {
        for (Node t: bagOfBags) {
            if (t.getValue().equals(bag)) {
                return t;
            }
            LinkedList<Node> parents = t.getParents();
            if (parents != null) {
                for (Node n: parents) {
                    if (n.getValue().equals(bag)) {
                        return n;
                    }
                }
            }
        }
        return null;
    }

    public static int parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        while (line != null) {
            check(line);
            line = bufferedReader.readLine();
        }

        Node goldBag = getBag("shiny gold");
        assert goldBag != null;
        LinkedList<Node> containers = goldBag.getParents();
        System.out.println("  " + Arrays.deepToString(containers.toArray()));

        LinkedList<Node> isAlreadyCounted = new LinkedList<>();
        Node tmp;
        while (!containers.isEmpty()) {
            tmp = containers.remove();
            if (!isAlreadyCounted.contains(tmp)) {
                isAlreadyCounted.add(tmp);
                System.out.println(tmp.getValue() + " + " + tmp.getParents().size());
                containers.addAll(tmp.getParents());
                count++;
            }
        }

        System.out.println();
        return count;
    }

    public static void main(String[] args) {
        String fileName = "src\\main\\resources\\input7.txt";
        try {
            BufferedReader bufferedReader = InputReader.read(fileName);
            parse(bufferedReader);
            System.out.println("Solution is " + count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class Node {
        private final LinkedList<Node> parents = new LinkedList<>();
        private final String value;

        public Node(String value) {
            this.value = value;
        }

        public void addParent(Node node) {
            parents.add(node);
        }

        public LinkedList<Node> getParents() {
            return parents;
        }

        public String getValue() {
            return value;
        }
    }
}

