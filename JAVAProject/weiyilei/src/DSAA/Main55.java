package DSAA;

import java.io.*;
import java.math.*;
import java.util.*;

class node{
    int val;
    node next;
    node prev;
    node(int a){
        this.val = a;
    }
    node(){}
}

public class Main55 {
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
            int[] sort = new int[100001];
            int[] top = new int[300001];
            int i,kind;
            int num = 0;
            String str;
            node[] nodes = new node[300001];
            node[] now = new node[300001];
            for(i = 0;i < 300001;i++){
                nodes[i] = new node();
                now[i] = nodes[i];
            }
            boolean judge = true;
            while(judge){
                str = in.next();
                switch (str){
                    case "put-in":
                        kind = in.nextInt();
                        sort[kind] = sort[kind] + 1;
                        if (num < sort[kind]){
                            num = num + 1;
                        }
                        top[sort[kind]] = top[sort[kind]] + 1;
                        now[sort[kind]].next = new node(kind);
                        now[sort[kind]].next.prev = now[sort[kind]];
                        now[sort[kind]] = now[sort[kind]].next;
                        break;
                    case "eat":
                        if(num < 1){
                            out.println("pa");
                        }
                        else{
                            out.println(now[num].val);
                            if(top[num] > 1){
                                top[num] = top[num] - 1;
                                sort[now[num].val] = sort[now[num].val] - 1;
                                now[num] = now[num].prev;
                                now[num].next = null;
                            }
                            else{
                                top[num] = top[num] - 1;
                                sort[now[num].val] = sort[now[num].val] - 1;
                                now[num] = now[num].prev;
                                now[num].next = null;
                                num = num - 1;
                            }
                        }
                        break;
                    case"nsdd":
                        judge = false;
                        break;
                }
            }
            /*while(str != "nsdd"){
                str = in.next();
                if(str == "put-in"){
                    kind = in.nextInt();
                    sort[kind] = sort[kind] + 1;
                    if(num < sort[kind]){
                        num = num + 1;
                    }
                    top[sort[kind]] = top[sort[kind]] + 1;
                    now[sort[kind]].next = new node(kind);
                    now[sort[kind]].next.prev = now[sort[kind]];
                    now[sort[kind]] = now[sort[kind]].next;
                }
                else if(str == "eat"){
                    if(num < 1){
                        out.println("pa");
                    }
                    else{
                        out.println(now[num].val);
                        if(top[num] <= 1){
                            top[num] = top[num] - 1;
                            sort[now[num].val] = sort[now[num].val] - 1;
                            now[num] = now[num].prev;
                            now[num].next = null;
                            num = num - 1;
                        }
                        else{
                            top[num] = top[num] - 1;
                            sort[now[num].val] = sort[now[num].val] - 1;
                            now[num] = now[num].prev;
                            now[num].next = null;
                        }
                    }
                }
                else{
                    break;
                }
            }*/
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