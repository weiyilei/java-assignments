package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main61 {

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
            int i,pre = 0,su = 0,j,ans;
            String p1 = "",p2 = "",str;
            String str1 = in.next();
            String str2 = in.next();
            if(str1.length() > str2.length()){
                for(i = 0;i < str2.length();i++){
                    if(str1.charAt(i) == str2.charAt(i)){
                        pre++;
                    }
                    else{
                        break;
                    }
                }
                p1 = str2.substring(0,pre);
                for(i = 0;i < str2.length();i++){
                    if(str1.charAt(str1.length() - 1 - i) == str2.charAt(str2.length() - 1 - i)){
                        su++;
                    }
                    else{
                        break;
                    }
                }
                p2 = str2.substring(str2.length() - su);
            }
            else{
                for(i = 0;i < str1.length();i++){
                    if(str1.charAt(i) == str2.charAt(i)){
                        pre++;
                    }
                    else{
                        break;
                    }
                }
                p1 = str1.substring(0,pre);
                for(i = 0;i < str1.length();i++){
                    if(str1.charAt(str1.length() - 1 - i) == str2.charAt(str2.length() - 1 - i)){
                        su++;
                    }
                    else{
                        break;
                    }
                }
                p2 = str1.substring(str1.length() - su);
            }
            for(i = 3;i <= n;i++){
                pre = 0;
                su = 0;
                str = in.next();
                if(str.length() < p1.length()){
                    for(j = 0;j < str.length();j++){
                        if(str.charAt(j) == p1.charAt(j)){
                            pre++;
                        }
                        else{
                            break;
                        }
                    }
                    p1 = str.substring(0,pre);
                }
                else{
                    for(j = 0;j < p1.length();j++){
                        if(str.charAt(j) == p1.charAt(j)){
                            pre++;
                        }
                        else{
                            break;
                        }
                    }
                    p1 = p1.substring(0,pre);
                }
                if(str.length() < p2.length()){
                    for(j = 0;j < str.length();j++){
                        if(str.charAt(str.length() - 1 - j) == p2.charAt(p2.length() - 1 - j)){
                            su++;
                        }
                        else{
                            break;
                        }
                    }
                    p2 = str.substring(str.length() - su);
                }
                else{
                    for(j = 0;j < p2.length();j++){
                        if(str.charAt(str.length() - 1 - j) == p2.charAt(p2.length() - 1 - j)){
                            su++;
                        }
                        else{
                            break;
                        }
                    }
                    p2 = p2.substring(p2.length() - su);
                }
            }
            pre = p1.length();
            su = p2.length();
            ans = pre * su;
            out.println(ans);
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