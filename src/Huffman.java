import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Huffman {
   static Map<Character,String> codes = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Map frequencies = getFrequencies();
        Node root = makeHuffmanTree(frequencies);
        getCodes(root,"");
        System.out.println(codes.get('T'));
        System.out.println(codes.get('h'));
        System.out.println(codes.get('e'));
        FileReader file = new FileReader(new File ("inputText.txt"));
        BitOutputStream stream = new BitOutputStream("binaryOutput.txt");
        int c;
        while (( c =  file.read()) != -1){
            String bits = codes.get((char)(c));
            stream.writeBits(bits.length(), Integer.parseInt(bits,2));
        }
        stream.close();
        file.close();
        BitInputStream codedBits = new BitInputStream("binaryOutput.txt");
        FileWriter decodedTextFile = new FileWriter("finalOutput.txt");
        Node temp = root;
        boolean hasBits = true;

        while (hasBits){
            int bit = codedBits.readBits(1);

            if (bit == -1) {
                hasBits = false;
                break;
            }
            if (temp.isLeaf()&& temp.c != null){
                System.out.print(temp.c);
                decodedTextFile.write(temp.c);
                temp = root;
            }
            if (bit ==0) temp = temp.left;
            if (bit==1) temp = temp.right;

        }
        decodedTextFile.flush();
        decodedTextFile.close();




    }

    private static Node makeHuffmanTree(Map frequencies) {
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
        return root;
    }

    private static void getCodes(Node root, String code){
        if (root.left == null && root.right == null&& root.c != null){
            codes.put(root.c,code);
            return;
        }
        getCodes(root.left,code +"0");
        getCodes(root.right,code+"1");
    }
    private static Map<Character,Integer> getFrequencies() throws IOException {
        Map<Character, Integer> map = new HashMap<>();
        FileReader console = new FileReader(new File ("inputText.txt"));
        int a;
        while((a = console.read()) !=-1) {
            Character c =(char) (a);
            map.putIfAbsent(c,0);
            map.put(c, map.get(c) +1);
        }
        console.close();
        return map;
    }
}

