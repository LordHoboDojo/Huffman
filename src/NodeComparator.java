import java.util.Comparator;

public class NodeComparator implements Comparator<Node> {

    @Override
    public int compare(Node t1, Node t2) {
        return t1.frequency - t2.frequency;
    }
}
