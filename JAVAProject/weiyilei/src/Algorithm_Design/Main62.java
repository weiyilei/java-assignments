package Algorithm_Design;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;
import java.io.*;

public class Main62 {

    static class Edge implements Comparable<Edge>{
        Node u;
        Node v;
        int weight;
        public Edge(Node a,Node b,int weight){
            this.u = a;
            this.v = b;
            this.weight = weight;
        }
        @Override
        public int compareTo(Edge e){
            if(this.weight < e.weight) {
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    static class Node{
        int num;
        public Node(int num){
            this.num = num;
        }
        public int getNum(){
            return this.num;
        }
    }

    public static void Initial(int n){
        for(int i = 1;i <= n;i++){
            temp[i] = i;
        }
    }

    public static long[] Fibonacci = new long[200];
    public static Edge[] edges;
    public static Node[] nodes;
    public static int[] temp;
    public static int ans;

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        Fibonacci[0] = 0;
        Fibonacci[1] = 1;
        Fibonacci[2] = 1;
        int i,t = in.nextInt(),n,m,j,u,v,w,totalWeight1,totalWeight2,a,b;
        for(i = 3;i < 200;i++){
            Fibonacci[i] = Fibonacci[i - 1] + Fibonacci[i - 2];
        }
        for(i = 1;i <= t;i++){
            ans = 0;
            totalWeight1 = 0;
            totalWeight2 = 0;
            n = in.nextInt();
            nodes = new Node[100001];
            temp = new int[100001];
            Map<Integer,Node> map = new HashMap<>();
            for(j = 1;j <= n;j++){
                nodes[j] = new Node(j);
                map.put(j,nodes[j]);
            }
            m = in.nextInt();
            edges = new Edge[100001];
            for(j = 1;j <= m;j++){
                u = in.nextInt();
                v = in.nextInt();
                w = in.nextInt();
                edges[j] = new Edge(map.get(u),map.get(v),w);
            }
            Arrays.sort(edges,1,m + 1);
            Initial(n);
            for(j = 1;j <= m;j++){
                if(ans + 1 == n){
                    break;
                }
                a = recursion(edges[j].u.getNum());
                b = recursion(edges[j].v.getNum());
                if(a != b){
                    ans = ans + 1;
                    totalWeight1 = totalWeight1 + edges[j].weight;
                    temp[a] = b;
                }
            }
            if(ans < n - 1){
                out.println("No");
                continue;
            }
            ans = 0;
            Initial(n);
            for(j = m;j > 0;j--){
                if(ans + 1 == n){
                    break;
                }
                a = recursion(edges[j].u.getNum());
                b = recursion(edges[j].v.getNum());
                if(a != b){
                    ans = ans + 1;
                    totalWeight2 = totalWeight2 + edges[j].weight;
                    temp[a] = b;
                }
            }
            if(ans < n - 1){
                out.println("No");
                continue;
            }
            for(j = 1;;j++){
                if(Fibonacci[j] > totalWeight2){
                    out.println("No");
                    break;
                }
                else if(Fibonacci[j] >= totalWeight1 && Fibonacci[j] <= totalWeight2){
                    out.println("Yes");
                    break;
                }
            }
        }
        out.close();
    }

    public static int recursion(int i){
        if(temp[i] != i){
            return temp[i] = recursion(temp[i]);
        }
        else{
            return i;
        }
    }


    static class Reader
    {
        final private int BUFFER_SIZE = 1 << 16;
        private DataInputStream din;
        private byte[] buffer;
        private int bufferPointer, bytesRead;

        public Reader()
        {
            din = new DataInputStream(System.in);
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public Reader(String file_name) throws IOException
        {
            din = new DataInputStream(new FileInputStream(file_name));
            buffer = new byte[BUFFER_SIZE];
            bufferPointer = bytesRead = 0;
        }

        public String readLine() throws IOException
        {
            byte[] buf = new byte[64]; // line length
            int cnt = 0, c;
            while ((c = read()) != -1)
            {
                if (c == '\n')
                    break;
                buf[cnt++] = (byte) c;
            }
            return new String(buf, 0, cnt);
        }

        public int nextInt() throws IOException
        {
            int ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do
            {
                ret = ret * 10 + c - '0';
            }  while ((c = read()) >= '0' && c <= '9');

            if (neg)
                return -ret;
            return ret;
        }

        public long nextLong() throws IOException
        {
            long ret = 0;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();
            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');
            if (neg)
                return -ret;
            return ret;
        }

        public double nextDouble() throws IOException
        {
            double ret = 0, div = 1;
            byte c = read();
            while (c <= ' ')
                c = read();
            boolean neg = (c == '-');
            if (neg)
                c = read();

            do {
                ret = ret * 10 + c - '0';
            }
            while ((c = read()) >= '0' && c <= '9');

            if (c == '.')
            {
                while ((c = read()) >= '0' && c <= '9')
                {
                    ret += (c - '0') / (div *= 10);
                }
            }

            if (neg)
                return -ret;
            return ret;
        }

        private void fillBuffer() throws IOException
        {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1)
                buffer[0] = -1;
        }

        private byte read() throws IOException
        {
            if (bufferPointer == bytesRead)
                fillBuffer();
            return buffer[bufferPointer++];
        }

        public void close() throws IOException
        {
            if (din == null)
                return;
            din.close();
        }
    }

}