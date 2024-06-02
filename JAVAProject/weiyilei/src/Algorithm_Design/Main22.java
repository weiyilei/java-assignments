package Algorithm_Design;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main22 {

    public static int[][] adjacent = new int[21][21];
    public static int[] daxiaoxie = new int[21];
    public static int Max = 0;

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
            int i,j,k,rst = 0;
            String s,a,b;
            Map<String, Integer> Map = new LinkedHashMap();
            Map.put("b",0);Map.put("c",1);Map.put("d",2);Map.put("f",3);Map.put("g",4);Map.put("h",5);Map.put("j",6);Map.put("k",7);Map.put("l",8);
            Map.put("m",9);Map.put("n",10);Map.put("p",11);Map.put("q",12);Map.put("r",13);Map.put("s",14);Map.put("t",15);Map.put("v",16);Map.put("w",17);
            Map.put("x",18);Map.put("y",19);Map.put("z",20);
            for(i = 1;i <= t;i++) {
                for(j = 0;j <= 20;j++){
                    daxiaoxie[j] = 0;
                    for(k = 0;k <= 20;k++){
                        adjacent[j][k] = 0;
                    }
                }
                Max = 0;
                rst = 0;
                s = in.next();
                for (j = 0; j < s.length() - 1; j++) {
                    a = String.valueOf(s.charAt(j));
                    b = String.valueOf(s.charAt(j + 1));
                    if (!a.equals("a") && !b.equals("a") && !a.equals("e") && !b.equals("e") && !a.equals("i") && !b.equals("i") &&
                            !a.equals("o") && !b.equals("o") && !a.equals("u") && !b.equals("u") && !a.equals(b)) {
                        adjacent[Map.get(a)][Map.get(b)] += 1;
                        adjacent[Map.get(b)][Map.get(a)] += 1;
                    }
                }
                dfs(1, rst);
                out.println(Max);
            }
        }
    }

    public static void dfs(int shendu,int rst){
        int m;
        if(shendu > 20){
            if(Max < rst) {
                Max = rst;
            }
            return;
        }
        else {
            dfs(shendu + 1, rst);
            daxiaoxie[shendu] = 1;
            for (m = 0; m <= 20; m++) {
                if (daxiaoxie[m] != 0){
                    rst = rst - adjacent[shendu][m];
                }
                else{
                    rst = rst + adjacent[shendu][m];
                }
            }
            if(Max < rst) {
                Max = rst;
            }
            dfs(shendu + 1,rst);
            daxiaoxie[shendu] = 0;
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