import java.util.HashMap;
import java.util.Map;

public class Node {
    int frequency;
    Character c;
    Node right;
    Node left;

    public Node(Object frequency, Object c, Node right, Node left) {
        this.frequency = (int) frequency;
        this.c = (Character) c;
        this.right= right;
        this.left = left;
    }
}
