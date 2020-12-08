import java.util.LinkedList;

public class Tree {
    private final Node root;

    public Tree(String value) {
        root = new Node(value);
    }

    public void addChild(String child) {
        root.addChild(new Node(child));
    }

    public boolean contains(String key) {
        return root.contains(key);
    }

    private class Node{
        private LinkedList<Node> children;
        private String value;

        public Node(String value) {
            this.value = value;
        }

        public void addChild(Node node) {
            children.add(node);
        }

        public LinkedList<Node> getChildren() {
            return children;
        }

        public String getValue() {
            return value;
        }

        public boolean contains(String key) {
            if (value.equals(key)) {
                return true;
            } else {
                for (Node child: children) {
                    if (child.contains(key)) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
