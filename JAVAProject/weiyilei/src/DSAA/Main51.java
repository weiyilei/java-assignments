package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main51 {

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
            int i,n,j,top;
            String str;
            for(i = 1;i <= t;i++){
                n = in.nextInt();
                str = in.next();
                int[] array = new int[n];
                top = -1;
                for(j = 0;j < n;j++){
                    if(str.charAt(j) == '{'){
                        top = push(array,1,top);
                    }
                    else if(str.charAt(j) == '}'){
                        if(top >= 0){
                            if(array[top] == 1){
                                top = pop(array,top);
                            }
                            else{
                                top = -2;
                                break;
                            }
                        }
                        else{
                            top = -2;
                            break;
                        }
                    }
                    else if(str.charAt(j) == '['){
                        top = push(array,2,top);
                    }
                    else if(str.charAt(j) == ']'){
                        if(top >= 0){
                            if(array[top] == 2){
                                top = pop(array,top);
                            }
                            else{
                                top = -2;
                                break;
                            }
                        }
                        else{
                            top = -2;
                            break;
                        }
                    }
                    else if(str.charAt(j) == '('){
                        top = push(array,3,top);
                    }
                    else if(str.charAt(j) == ')'){
                        if(top >= 0){
                            if(array[top] == 3){
                                top = pop(array,top);
                            }
                            else{
                                top = -2;
                                break;
                            }
                        }
                        else{
                            top = -2;
                            break;
                        }
                    }
                }
                if(top == -1){
                    out.println("YES");
                }
                else{
                    out.println("NO");
                }
            }
        }
    }

    public static int push(int[] array,int a,int top){
        if(top < array.length - 1){
            top = top + 1;
            array[top] = a;
        }
        return top;
    }

    public static int pop(int[] array,int top){
        if(top >= 0){
            array[top] = 0;
            top = top - 1;
        }
        return top;
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