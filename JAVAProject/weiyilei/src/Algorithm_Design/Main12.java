package Algorithm_Design;

import java.io.*;
import java.math.*;
import java.util.*;

public class Main12 {

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
            String nm;
            int i,dog,j;
            Woman w = new Woman();
            int[] domination = new int[n];
            Man[] man = new Man[n];
            Woman[] woman = new Woman[n];
            Map<String,Man> M = new LinkedHashMap<>();
            Map<String,Woman> W = new LinkedHashMap<>();
            for(i = 0;i < n;i++){
                nm = in.next();
                man[i] = new Man(nm,i,n);
            }
            for(i = 0;i < n;i++){
                nm = in.next();
                woman[i] = new Woman(nm,i,n);
            }
            for(i = 0;i < n;i++){
                domination[i] = 0;
            }
            dog = n;
            for(i = 0;i < n;i++){
                for(j = 0;j < n;j++){
                    nm = in.next();
                    man[i].prefer[j] = nm;
                }
            }
            for(i = 0;i < n;i++){
                for(j = 0;j < n;j++){
                    nm = in.next();
                    woman[i].prefer[j] = nm;
                }
            }
            for(i = 0;i < n;i++){
                M.put(man[i].name,man[i]);
            }
            for(i = 0;i < n;i++){
                W.put(woman[i].name,woman[i]);
            }
            while(dog > 0){
                for(j = 0;j < n;){
                    if(man[j].state == -1){
                        w = W.get(man[j].prefer[domination[j]]);
                        if(w.state == -1){
                            w.setState(j);
                            man[j].setState(w.num);
                            dog--;
                            j++;
                        }
                        else{
                            if(w.FindRanking(man[w.state].name) < w.FindRanking(man[j].name)){
                                domination[j]++;
                            }
                            else{
                                man[w.state].state = -1;
                                domination[w.state]++;
                                w.setState(j);
                                man[j].setState(w.num);
                                j++;
                            }
                        }
                    }
                    else{
                        j++;
                    }
                }
            }
            for(i = 0;i < n;i++){
                out.println(man[i].name + " " + woman[man[i].state].name);
            }
        }
    }

    static class Man{
        String name;
        int num;
        String[] prefer;
        int state;
        public Man(String name,int num,int n){
            this.name = name;
            this.num = num;
            prefer = new String[n];
            this.state = -1;
        }
        public void setState(int a){
            this.state = a;
        }
    }

    static class Woman{
        String name;
        int num;
        String[] prefer;
        int state;
        public Woman(String name,int num,int n){
            this.name = name;
            this.num = num;
            this.state = -1;
            prefer = new String[n];
        }
        public Woman(){};
        public void setState(int a){
            this.state = a;
        }
        public int FindRanking(String a){
            int ranking,k;
            for(k = 0;!this.prefer[k].equals(a);k++){}
            ranking = k;
            return ranking;
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