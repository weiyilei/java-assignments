package Algorithm_Design;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main72 {

    static Node[] nodes = new Node[26];

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Node implements Comparable<Node>{
        int num;
        int amount;
        Node left;
        Node right;
        int degree;
        public Node(int num,int amount){
            this.num = num;
            this.amount = amount;
        }

        @Override public int compareTo(Node n){
            if(this.amount < n.amount){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    public static Node getHuffmanTree(PriorityQueue<Node> queue){
        while(queue.size() > 1){
            Node left = queue.remove();
            Node right = queue.remove();
            Node parent = new Node(-1,left.amount + right.amount);
            parent.left = left;
            parent.right = right;
            queue.add(parent);
        }
        return queue.remove();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int n = in.nextInt();
            int i,j,ans;
            String str;
            Node root,temp;
            for(i = 0;i < n;i++){
                ans = 0;
                str = in.next();
                for(j = 0;j < 26;j++){
                    nodes[j] = new Node(j,0);
                }
                for(j = 0;j < str.length();j++){
                    nodes[str.charAt(j) - 'a'].amount++;
                }
                PriorityQueue<Node> priorityQueue = new PriorityQueue<>(Node::compareTo);
                Queue<Node> queue = new LinkedList<Node>();
                for(j = 0;j < 26;j++){
                    if(nodes[j].amount != 0){
                        priorityQueue.add(nodes[j]);
                    }
                }
                root = getHuffmanTree(priorityQueue);
                root.degree = 0;
                queue.add(root);
                while(!queue.isEmpty()){
                    temp = queue.remove();
                    if(temp.num >= 0){
                        ans = ans + temp.amount * temp.degree;
                    }
                    if(temp.left != null){
                        temp.left.degree = temp.degree + 1;
                        queue.add(temp.left);
                    }
                    if(temp.right != null){
                        temp.right.degree = temp.degree + 1;
                        queue.add(temp.right);
                    }
                }
                out.println(ans);
            }
        }
    }






    static class InputReader {
        public BufferedReader reader;
        public StringTokenizer tokenizer;

        public InputReader(InputStream stream) {
            reader = new BufferedReader(new InputStreamReader(stream), 32768);
            tokenizer = null;
        }

        public String next() {
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] nextCharArray() {
            return next().toCharArray();
        }

        //         public boolean hasNext() {
//             try {
//                 return reader.ready();
//             } catch(IOException e) {
//                 throw new RuntimeException(e);
//             }
//         }
        public boolean hasNext() {
            try {
                String string = reader.readLine();
                if (string == null) {
                    return false;
                }
                tokenizer = new StringTokenizer(string);
                return tokenizer.hasMoreTokens();
            } catch (IOException e) {
                return false;
            }
        }

        public BigInteger nextBigInteger() {
            return new BigInteger(next());
        }

        public BigDecimal nextBigDecinal() {
            return new BigDecimal(next());
        }
    }
}