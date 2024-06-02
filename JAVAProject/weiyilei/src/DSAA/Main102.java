package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main102 {

    public static void main(String[] args) {
        InputStream inputStream = System.in;// new FileInputStream("C:\\Users\\wavator\\Downloads\\test.in");
        OutputStream outputStream = System.out;
        InputReader in = new InputReader(inputStream);
        PrintWriter out = new PrintWriter(outputStream);
        Task solver = new Task();
        solver.solve(in, out);
        out.close();
    }

    static class Task {

        public void solve(InputReader in, PrintWriter out) {
            int n,m,i,j,a,b,p,u;
            n = in.nextInt();
            m = in.nextInt();
            int[] in1 = new int[n + 1],in2 = new int[n + 1];
            ArrayList<Integer>[] array = new ArrayList[n+1];
            ArrayList<Integer>[] ARRAY = new ArrayList[n+1];
            Stack<Integer> S = new Stack<>(),SB = new Stack<>();
            boolean[] is = new boolean[n+1];
            boolean s,isEmpty;
            for(i = 0;i < n;i++){
                array[i + 1] = new ArrayList<>();
                ARRAY[i + 1] = new ArrayList<>();
            }
            for(i = 1;i <= m;i++){
                a = in.nextInt();
                b = in.nextInt();
                array[a].add(b);
                ARRAY[b].add(a);
                in1[b] = in1[b] + 1;
                in2[a] = in2[a] + 1;
            }
            for(i = 0;i < n;i++){
                is[i + 1] = false;
            }
            S.push(1);
            s = true;
            while(!S.isEmpty()){
                p = S.peek();
                u = 0;
                isEmpty = true;
                for(i = 0;i < array[p].size();i++){
                    if(!is[array[p].get(i)]){
                        u = array[p].get(i);
                        isEmpty = false;
                        break;
                    }
                }
                if(isEmpty){
                    S.pop();
                    if(p == 1){
                        for(i = 1;i <= n;i++){
                            if(!is[i]) {
                                s = false;
                                break;
                            }
                        }
                        break;
                    }
                }
                else{
                    S.push(u);
                    is[u] = true;
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
        }*/
            if(!s){
                out.print("wawawa");
            }
            else{
                for(i = 1;i <= n;i++){
                    is[i] = false;
                }
                SB = new Stack<>();
                SB.push(1);
                while (!SB.isEmpty()){
                    p = SB.peek();
                    u = 0;
                    isEmpty = true;
                    for(i = 1;i <= ARRAY[p].size();i++){
                        if(!is[ARRAY[p].get(i - 1)]){
                            u = ARRAY[p].get(i - 1);
                            isEmpty = false;
                            break;
                        }
                    }
                    if(isEmpty){
                        SB.pop();
                        if(p == 1){
                            for(i = 0;i < n;i++){
                                if(!is[i + 1]){
                                    s = false;
                                    break;
                                }
                            }
                            break;
                        }
                    }
                    else{
                        SB.push(u);
                        is[u] = true;
                    }
                }
                if (!s){
                    out.print("wawawa");
                } else {
                    out.print("Bravo");
                }
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