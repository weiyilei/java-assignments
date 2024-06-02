package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main101 {

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
            int n = in.nextInt();
            int m = in.nextInt();
            int[][] array = new int[n+1][n+1];
            int i,u,v,w,j,min,k;
            long rst;
            int[] d = new int[n + 1],cl = new int[n + 1];
            boolean[] c = new boolean[n + 1];
            for(i = 1;i <= m;i++){
                u = in.nextInt();
                v = in.nextInt();
                w = in.nextInt();
                array[u][v] = w;
                array[v][u] = w;
            }
            for(i = 1;i <= n;i++){
                for(j = 1;j <= n;j++){
                    if(array[i][j] == 0){
                        array[i][j] = -1;
                    }
                }
            }
            rst = 0;
            c[1] = true;
            for(i = 1;i < n;i++){
                d[i + 1] = array[1][i + 1];
                cl[i + 1] = 1;
                c[i + 1] = false;
            }
            for(i = 1;i < n;i++){
                min = Integer.MAX_VALUE;
                j = 1;
                for(k = 1;k < n;k++){
                    if(d[k + 1] != -1 && d[k + 1] < min && !c[k + 1]){
                        min = d[k + 1];
                        j = k + 1;
                    }
                }
                c[j] = true;
                for (k = 1;k < n;k++){
                    if (!c[k + 1] && array[j][k + 1] != -1){
                        if (array[j][k + 1] < d[k + 1] || d[k + 1] == -1){
                            d[k + 1] = array[j][k + 1];
                            cl[k + 1] = j;
                        }
                    }
                }
            }
            for(i = 1;i <= n;i++){
                rst = rst + d[i];
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
            out.print(rst);
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