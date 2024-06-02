package DSAA;

import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

public class Main112 {

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
            Stack<Integer> a = new Stack<>(), b = new Stack<>();
            String op;
            int c, p;
            int[] sum = new int[1000006];
            int[] f = new int[1000006];
            f[0] = -1000000000;
            p = 0;
            while (n > 0) {
                op = in.next();
                if (op.equals("I")) {
                    c = in.nextInt();
                    a.push(c);
                    update(++p, c, sum, f);
                } else if (op.equals("D")) {
                    a.pop();
                    --p;
                } else if (op.equals("L")) {
                    if (!a.empty()) {
                        b.push(a.peek());
                        a.pop();
                        --p;
                    }
                } else if (op.equals("R")) {
                    if (!b.empty()) {
                        c = b.peek();
                        a.push(c);
                        b.pop();
                        update(++p, c, sum, f);
                    }
                } else if (op.equals("Q")) {
                    c = in.nextInt();
                    out.println(f[c]);
                }
                n--;
            }
        }
        void update(int p,int c,int[] sum,int[] f) {
            sum[p] = sum[p - 1] + c;
            f[p] = Math.max(f[p - 1], sum[p]);
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