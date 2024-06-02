package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main52 {

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
            int i;
            char ch;
            int[] array = new int[20000000];
            int front = 0,rear = 0;
            for(i = 1;i <= n;i++){
                ch = in.next().charAt(0);
                if(ch == 'E'){
                    array[rear] = in.nextInt();
                    rear = rear + 1;
                }
                else if(ch == 'D'){
                    if(front < rear){
                        array[front] = 0;
                        front = front + 1;
                    }
                }
                else if(ch == 'A'){
                    out.println(array[front]);
                }
            }
            if(front == rear){
                out.println();
            }
            else if(front == rear - 1){
                out.println(array[front]);
            }
            else{
                for(i = front;i < rear - 1;i++){
                    out.print(array[i] +" ");
                }
                out.println(array[rear - 1]);
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