package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main46 {

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
            int i,j,n,judge,station,k,num;
            String str,ch;
            char[] array = new char[210000];
            char[] array1 = new char[210000];
            for(i = 1;i <= t;i++){
                str = in.next();
                num = str.length();
                for(j = 0;j < num;j++){
                    array[j] = str.charAt(j);
                }
                n = in.nextInt();
                for(j = 1;j <= n;j++){
                    judge = in.nextInt();
                    if(judge == 1){
                        ch = in.next();
                        station = in.nextInt();
                        System.arraycopy(array,station - 1,array,station,num - station + 1);
                        array[station - 1] = ch.charAt(0);
                        num = num + 1;
                    }
                    else{
                        station = in.nextInt();
                        out.println(array[station - 1]);
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