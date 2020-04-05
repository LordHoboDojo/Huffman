import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
    public static void main(String[] args) throws Exception {
        Map frequencies = getFrequencies();
        PriorityQueue<Node> q = new PriorityQueue<>(new NodeComparator());
        frequencies.forEach((key,value) -> {
            q.add(new Node(value,key,null,null));
                });
        Node root = null;
        while (q.size()>1){
            Node firstExtract = q.poll();
            Node secondExtract = q.poll();
            Node sum = new Node (firstExtract.frequency+secondExtract.frequency,null,firstExtract,secondExtract);
            root = sum;
            q.add(sum);
        }
        printCode(root,"");
    }
    private static void printCode(Node root,String code){
        if (root.left == null && root.right == null&& root.c != null){
            System.out.println(root.c + ":"+ code);
            return;
        }
        printCode(root.left,code +"0");
        printCode(root.right,code+"1");
    }
    private static Map<Character,Integer> getFrequencies() throws FileNotFoundException {
        Map<Character, Integer> map = new HashMap<>();
        Scanner console = new Scanner(new File("text.txt"));
        while(console.hasNextLine()) {
            String line = console.nextLine();
            char[] chars =line.toCharArray();
            for(int i=0;i<chars.length;i++) {
                Character x = chars[i];
                if (map.get(chars[i])==null) {
                    map.put(x,1);
                }
                else {
                    Integer num= map.get(x);
                    num++;
                    map.put(x, num);
                }
            }
        }
        return map;
    }
}

