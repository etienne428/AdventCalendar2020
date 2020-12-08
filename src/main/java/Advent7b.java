import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Advent7b {

    private static LinkedList<Node> bagOfBags = new LinkedList<>();
    static int count = 0;

    private static void check(String input) {
//        System.out.println(input);
        String bag = input.split(" bags contain")[0];
        String[] childBags  = input.split(" bags contain")[1].split(",");
//        System.out.println(bags[0]);

        Node parentBag = getBag(bag);
        if (parentBag == null) {
            parentBag = new Node(bag);
            bagOfBags.add(parentBag);
        }
        if (!childBags[0].contains(" no ")) {
            int childLen = childBags.length;
            int cardinality = 0;
            for (int i = 0; i < childLen; i++) {
                cardinality = Integer.parseInt(childBags[i].substring(1, 2));
                        childBags[i] = childBags[i].substring(3, childBags[i].indexOf(" bag"));
                Node child = getBag(childBags[i]);
                if (child == null) {
                    child = new Node(childBags[i]);
                }
                bagOfBags.add(child);
                parentBag.addMap(child, cardinality);
//                System.out.println(bags[0] + ". !!! ." + bagContained[i] + ".");
            }
        }
    }

    private static Node getBag(String bag) {
        for (Node t: bagOfBags) {
            if (t.getValue().equals(bag)) {
                return t;
            }
        }
        return null;
    }

    public static void parse(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        int i;
        for (i = 0; line != null; i++) {

            check(line);
            line = bufferedReader.readLine();
        }

        Node goldBag = getBag("shiny gold");
        LinkedList<Node> containers = goldBag.getChildren();
        System.out.println(goldBag.value + " has " + goldBag.numberOfChild + " children : " + goldBag.getChildrenValues());

        count += goldBag.getNumberOfChild();
        Node child;
        while (!containers.isEmpty()) {
            child = containers.remove();
            System.out.println(child.value + " has " + child.numberOfChild + " children : " + child.getChildrenValues());
            count += child.getNumberOfChild();
            containers.addAll(child.getChildren());
        }


        //        count = goldBag.getParents().size();

        System.out.println();

    }

    public static void main(String[] args) {
        File file = new File("src\\main\\resources\\input7b.txt");
        try {
//            System.out.println("Attempting to read from file in: " + file.getCanonicalPath());
            BufferedReader bufferedReader;
            bufferedReader = new BufferedReader(new FileReader(file));
            parse(bufferedReader);
            System.out.println("Solution is " + count);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static class NodeMap implements Map {
        private final Node father;
        private final Node child;
        private final int cardinality;

        public NodeMap(Node father, Node child, int cardinality) {
            this.father = father;
            this.child = child;
            this.cardinality = cardinality;
            father.addMap(this);
        }

        public int getCardinality() {
            return cardinality;
        }

        public Node getChild() {
            return child;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object o) {
            return false;
        }

        @Override
        public boolean containsValue(Object o) {
            return false;
        }

        @Override
        public Object get(Object o) {
            return null;
        }

        @Override
        public Object put(Object o, Object o2) {
            return null;
        }

        @Override
        public Object remove(Object o) {
            return null;
        }

        @Override
        public void putAll(Map map) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set keySet() {
            return null;
        }

        @Override
        public Collection values() {
            return null;
        }

        @Override
        public Set<Entry> entrySet() {
            return null;
        }

    }

    static class Node{

        String value;
        LinkedList<NodeMap> children = new LinkedList<>();

        Node(String value) {
            this.value = value;
        }

        void addMap(Node child, int cardinality) {
            children.add(new NodeMap(this, child, cardinality));
        }

        public String getValue() {
            return value;
        }

        public LinkedList<Node> getChildren() {
            LinkedList<Node> childrenList = new LinkedList<>();
            for (NodeMap nm: children) {
                childrenList.add(nm.child);
            }
            return null;
        }
    }
}

