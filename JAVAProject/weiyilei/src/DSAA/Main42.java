/*import java.io.*;
import java.math.*;
import java.util.*;

class node{
    public int val;
    public node next;
    public node prev;
    node(int a){
        this.val = a;
    }
}

public class Algorithm_Design.Main42 {

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
            String str;
            node nowl,nowr,head,last,tmp;

        for(i = 1;i <= t;i++){
            n = in.nextInt();
            str = in.next();
            head = new node(-1);
            last = new node(-1);
            head.next = last;
            last.prev = head;
            nowl = head;
            nowr = last;

            for(j = 0;j < n;j++){
                if(str.charAt(j) == 'r'){
                    if(j != n - 1){
                        if( (Integer.parseInt(String.valueOf(str.charAt(j + 1))) >= 0) && (Integer.parseInt(String.valueOf(str.charAt(j + 1))) <= 9)  ){
                            if(nowr == last){
                                tmp = new node(Integer.parseInt(String.valueOf(str.charAt(j + 1))));
                                nowl.next = tmp;
                                tmp.prev = nowl;
                                tmp.next = nowr;
                                nowr.prev = tmp;
                                nowr = tmp;
                            }
                            else{
                                nowr.val = Integer.parseInt(String.valueOf(str.charAt(j + 1)));
                            }
                            j = j + 1;
                        }
                    }
                }
                else if(str.charAt(j) == 'H'){
                    if(nowl != head){
                        nowr = nowl;
                        nowl = nowl.prev;
                    }
                }
                else if(str.charAt(j) == 'L'){
                    if(nowr != last){
                        nowl = nowr;
                        nowr = nowr.next;
                    }
                }
                else if(str.charAt(j) == 'I'){
                    nowl = head;
                    nowr = head.next;
                }
                else if(str.charAt(j) == 'x'){
                    if(nowr != last){
                        if(nowr.next == last){
                            nowl.next = nowr.next;
                            nowr.prev = null;
                            nowr.next.prev = nowl;
                            nowr.next = null;
                            nowr = last;
                        }
                        else{
                            nowr.val = nowr.next.val;
                            nowr.next = nowr.next.next;
                            nowr.next.prev.next = null;
                            nowr.next.prev.prev = null;
                            nowr.next.prev = nowr;
                        }
                    }
                }
                else{
                    tmp = new node(Integer.parseInt(String.valueOf(str.charAt(j))));
                    nowl.next = tmp;
                    tmp.prev = nowl;
                    tmp.next = nowr;
                    nowr.prev = tmp;
                    nowl = tmp;
                }
            }
            nowl = head.next;
            while(nowl.val != -1){
                out.print(nowl.val);
                nowl = nowl.next;
            }
            out.println();
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
}*/