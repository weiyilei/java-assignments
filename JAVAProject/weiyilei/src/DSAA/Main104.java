package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main104 {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Node{
        Node p;
        int ind;
        int C;
        Node(Node p,int ind,int C){
            this.p = p;
            this.ind = ind;
            this.C = C;
        }
    }

    static class edge{
        Node v1;
        Node v2;
        long weight;
        edge(Node v1,Node v2,long weight){
            this.v1 = v1;
            this.v2 = v2;
            this.weight = weight;
        }
    }

    static class bh{
        int r,con,ci,max;
        edge[] hp;
        bh(int a){
            this.r = 0;
            this.con = a;
            this.hp = new edge[a];
        }
        edge deleteTop(){
            if(r == 0){
                return null;
            }
            r = r - 1;
            edge t = hp[0];
            hp[0] = hp[r];
            ci = 0;
            while(true){
                if(2 * ci + 1 >= r){
                    break;
                }
                if(2 * ci + 2 >= r){
                    max = 2 * ci + 1;
                }
                else{
                    max = hp[2 * ci + 1].weight < hp[2 * ci + 2].weight ? (2 *ci + 2) : (2 * ci + 1);
                }
                if(hp[ci].weight < hp[max].weight){
                    edge temp = hp[ci];
                    hp[ci] = hp[max];
                    hp[max] = temp;
                    ci = max;
                }
                else{
                    break;
                }
            }
            return t;
        }

        void insert(edge v){
            ci = r;
            hp[r] = v;
            r = r + 1;
            while(ci > 0){
                if(hp[ci].weight > hp[(ci - 1)/2].weight){
                    edge temp = hp[ci];
                    hp[ci] = hp[(ci - 1)/2];
                    hp[(ci - 1)/2] = temp;
                    ci = (ci - 1)/2;
                }
                else{
                    break;
                }
            }
        }
    }

    static Node find(Node a){
        if(a == a.p){
            return a;
        }
        else{
            a.p = find(a.p);
            return a.p;
        }
    }

    static void merge(Node a,Node b){
        find(a).p = find(b);
    }

    static class Task {
        public void solve(InputReader in, PrintWriter out) {
            int n,m,i,C;
            long ans;
            n = in.nextInt();
            m = in.nextInt();
            Node[] g = new Node[n * m];
            Node tN;
            edge tE;
            bh heap = new bh(1000000);
            for(i = 1;i <= n * m;i++){
                C = in.nextInt();
                tN = new Node(null,i - 1,C);
                tN.p = tN;
                g[i - 1] = tN;
            }
            for(i = 1;i <= n * m;i++){
                if((i - 1) % m != m - 1){
                    heap.insert(new edge(g[i - 1],g[i],(g[i - 1].C)*(g[i].C)));
                }
                if((i - 1) / m != n - 1){
                    heap.insert(new edge(g[i - 1],g[i - 1 + m],(g[i - 1].C)*(g[i - 1 + m].C)));
                }
            }
            ans = 0;
            while(heap.r != 0){
                tE = heap.deleteTop();
                if(find(tE.v1) != find(tE.v2)){
                    ans = ans + tE.weight;
                    merge(tE.v1,tE.v2);
                }
            }
            out.println(ans);
        }
    }
    /*
    import java.io.*;
import java.util.*;

class Untitled {
    public static void main(String[] args) throws IOException {
        AReader input = new AReader();
        AWriter output = new AWriter();
        int x;
        while (input.hasNext()) {
            x = input.nextInt();
            output.println(x);
        }
        output.close();
    }
}

class AReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException ex) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}

class AWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) throws IOException {
        writer.write(object.toString());
    }

    public void println(Object object) throws IOException {
        writer.write(object.toString());
        writer.write("\n");
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
    */

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