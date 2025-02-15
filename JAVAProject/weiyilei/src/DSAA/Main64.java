package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main64 {

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
            int t = in.nextInt();
            int i,n,k,j,len;
            String str;
            for(i = 1;i <= t;i++){
                str = in.next();
                n = str.length();
                int[] next = new int[n];
                k = 0;
                j = 1;
                next[0] = 0;
                while(j < n){
                    if(str.charAt(j) == str.charAt(k)){
                        k = k + 1;
                        next[j] = k;
                        j = j + 1;
                    }
                    else if(k == 0){
                        next[j] = 0;
                        j = j + 1;
                    }
                    else{
                        k = next[k - 1];
                    }
                }
                len = n - next[n - 1];
                if(next[n - 1] == 0){
                    out.println(n);
                }
                else{
                    if(n % len == 0){
                        out.println(0);
                    }
                    else{
                        out.println(len - (next[n - 1]%len));
                    }
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