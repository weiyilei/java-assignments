package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main66 {

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
            int[] word = new int[26];
            int i;
            for(i = 1;i <= 26;i++){
                word[in.next().charAt(0) - 'a'] = i;
            }
            StringBuilder str1 = new StringBuilder(in.next());
            int n = str1.length();
            StringBuilder str2 = new StringBuilder();
            StringBuilder str = new StringBuilder();
            for(i = 0;i < n;i++){
                switch (word[str1.charAt(i) - 'a']){
                    case 1:str2.append("a");break;
                    case 2:str2.append("b");break;
                    case 3:str2.append("c");break;
                    case 4:str2.append("d");break;
                    case 5:str2.append("e");break;
                    case 6:str2.append("f");break;
                    case 7:str2.append("g");break;
                    case 8:str2.append("h");break;
                    case 9:str2.append("i");break;
                    case 10:str2.append("j");break;
                    case 11:str2.append("k");break;
                    case 12:str2.append("l");break;
                    case 13:str2.append("m");break;
                    case 14:str2.append("n");break;
                    case 15:str2.append("o");break;
                    case 16:str2.append("p");break;
                    case 17:str2.append("q");break;
                    case 18:str2.append("r");break;
                    case 19:str2.append("s");break;
                    case 20:str2.append("t");break;
                    case 21:str2.append("u");break;
                    case 22:str2.append("v");break;
                    case 23:str2.append("w");break;
                    case 24:str2.append("x");break;
                    case 25:str2.append("y");break;
                    case 26:str2.append("z");break;
                }
            }
            str.append(str2);
            str.append(str1);
            int n1 = str.length();
            int[] next = new int[n1];
            int k = 0;
            int j = 1;
            next[0] = 0;
            while(j < n1){
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
            out.println(n - next[n1 - 1]);
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