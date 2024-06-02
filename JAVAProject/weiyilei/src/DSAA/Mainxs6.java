package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

public class Mainxs6 {

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
            int i,n,j;
            String ans;
            String a,b;
            for(i = 1;i <= t;i++){
                ans = "";
                n = in.nextInt();
                String[] x = new String[n/2];
                String[] y = new String[n/2];
                for(j = 0;j < n/2;j++) {
                    a = in.next();
                    if (a.equals(".-")) x[j] = "A";
                    else if (a.equals("-...")) x[j] = "B";
                    else if (a.equals("-.-.")) x[j] = "C";
                    else if (a.equals("-..")) x[j] = "D";
                    else if (a.equals(".")) x[j] = "E";
                    else if (a.equals("..-.")) x[j] = "F";
                    else if (a.equals("--.")) x[j] = "G";
                    else if (a.equals("....")) x[j] = "H";
                    else if (a.equals("..")) x[j] = "I";
                    else if (a.equals(".---")) x[j] = "J";
                    else if (a.equals("-.-")) x[j] = "K";
                    else if (a.equals(".-..")) x[j] = "L";
                    else if (a.equals("--")) x[j] = "M";
                    else if (a.equals("-.")) x[j] = "N";
                    else if (a.equals("---")) x[j] = "O";
                    else if (a.equals(".--.")) x[j] = "P";
                    else if (a.equals("--.-")) x[j] = "Q";
                    else if (a.equals(".-.")) x[j] = "R";
                    else if (a.equals("...")) x[j] = "S";
                    else if (a.equals("-")) x[j] = "T";
                    else if (a.equals("..-")) x[j] = "U";
                    else if (a.equals("...-")) x[j] = "V";
                    else if (a.equals(".--")) x[j] = "W";
                    else if (a.equals("-..-")) x[j] = "X";
                    else if (a.equals("-.--")) x[j] = "Y";
                    else if (a.equals("--..")) x[j] = "Z";
                    else if (a.equals("-----")) x[j] = "0";
                    else if (a.equals(".----")) x[j] = "1";
                    else if (a.equals("..---")) x[j] = "2";
                    else if (a.equals("...--")) x[j] = "3";
                    else if (a.equals("....-")) x[j] = "4";
                    else if (a.equals(".....")) x[j] = "5";
                    else if (a.equals("-....")) x[j] = "6";
                    else if (a.equals("--...")) x[j] = "7";
                    else if (a.equals("---..")) x[j] = "8";
                    else if (a.equals("----.")) x[j] = "9";
                }
                for(j = 0;j < n/2;j++) {
                    b = in.next();
                    if (b.equals(".-")) y[j] = "A";
                    else if (b.equals("-...")) y[j] = "B";
                    else if (b.equals("-.-.")) y[j] = "C";
                    else if (b.equals("-..")) y[j] = "D";
                    else if (b.equals(".")) y[j] = "E";
                    else if (b.equals("..-.")) y[j] = "F";
                    else if (b.equals("--.")) y[j] = "G";
                    else if (b.equals("....")) y[j] = "H";
                    else if (b.equals("..")) y[j] = "I";
                    else if (b.equals(".---")) y[j] = "J";
                    else if (b.equals("-.-")) y[j] = "K";
                    else if (b.equals(".-..")) y[j] = "L";
                    else if (b.equals("--")) y[j] = "M";
                    else if (b.equals("-.")) y[j] = "N";
                    else if (b.equals("---")) y[j] = "O";
                    else if (b.equals(".--.")) y[j] = "P";
                    else if (b.equals("--.-")) y[j] = "Q";
                    else if (b.equals(".-.")) y[j] = "R";
                    else if (b.equals("...")) y[j] = "S";
                    else if (b.equals("-")) y[j] = "T";
                    else if (b.equals("..-")) y[j] = "U";
                    else if (b.equals("...-")) y[j] = "V";
                    else if (b.equals(".--")) y[j] = "W";
                    else if (b.equals("-..-")) y[j] = "X";
                    else if (b.equals("-.--")) y[j] = "Y";
                    else if (b.equals("--..")) y[j] = "Z";
                    else if (b.equals("-----")) y[j] = "0";
                    else if (b.equals(".----")) y[j] = "1";
                    else if (b.equals("..---")) y[j] = "2";
                    else if (b.equals("...--")) y[j] = "3";
                    else if (b.equals("....-")) y[j] = "4";
                    else if (b.equals(".....")) y[j] = "5";
                    else if (b.equals("-....")) y[j] = "6";
                    else if (b.equals("--...")) y[j] = "7";
                    else if (b.equals("---..")) y[j] = "8";
                    else if (b.equals("----.")) y[j] = "9";
                }
                for(j = 0;j < n/2;j++){
                    ans = ans + x[j] + y[j];
                }
                out.println(ans);
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