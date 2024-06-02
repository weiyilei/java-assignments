package Algorithm_Design;

import java.io.*;
import java.util.*;

public class Main92 {

    static Edge[] edges;
    static Node[] nodes;

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt(),m = in.nextInt(),i,u,v,w,num1,num2;
        long temp,x,y,amount = 0;
        edges = new Edge[n - 1];
        nodes = new Node[n];
        Map<Integer, Node> map = new LinkedHashMap<>();
        int[] us = new int[n - 1],vs = new int[n - 1],ws = new int[n - 1],qs = new int[m];
        long[] rst;
        Node node1,node2,Node1,Node2;
        for(i = 1;i < n;i++){
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            us[i - 1] = u;
            vs[i - 1] = v;
            ws[i - 1] = w;
        }
        for(i = 0;i < m;i++){
            qs[i] = in.nextInt();
        }
        for(i = 1;i <= n;i++){
            nodes[i - 1] = new Node(i);
            map.put(i,nodes[i - 1]);
        }
        for(i = 1;i < n;i++){
            edges[i - 1] = new Edge();
        }
        for(i = 1;i < n;i++){
            edges[i - 1] = new Edge(map.get(us[i - 1]),map.get(vs[i - 1]),ws[i - 1]);
        }
        Arrays.sort(edges);
        temp = edges[0].wgt;

        rst = new long[(int)edges[edges.length - 1].wgt];
        for(Edge e : edges){
            while(e.wgt > temp){
                temp = temp + 1;
                rst[(int)(temp - 2)] = amount;
            }
            num1 = e.nodeLeft.num;
            num2 = e.nodeRight.num;
            node1 = map.get(num1);
            node2 = map.get(num2);
            Node1 = node1;
            Node2 = node2;
            while(Node1.hasParent()){
                Node1 = Node1.parent;
            }
            while(Node2.hasParent()){
                Node2 = Node2.parent;
            }
            x = Node1.degree;
            y = Node2.degree;
            if(!Node2.equals(Node1)){
                if(Node2.degree >= Node1.degree){
                    Node2.degree = Node2.degree + Node1.degree;
                    Node1.parent = Node2;
                }
                else if(Node2.degree < Node1.degree){
                    Node1.degree = Node1.degree + Node2.degree;
                    Node2.parent = Node1;
                }
            }
            amount = amount + x*y;
        }
        StringBuilder ans = new StringBuilder();

        rst[(int)(temp - 1)] = amount;
        for(int q : qs){
            if(q > edges[edges.length - 1].wgt) ans.append(rst[(edges[edges.length - 1].wgt - 1)]).append(" ");
            else if(q < edges[0].wgt) ans.append(0).append(" ");
            else ans.append(rst[q - 1]).append(" ");
        }
        out.println(ans);
        out.close();
    }

    static class Edge implements Comparable<Edge> {
        public Edge(){}
        Node nodeLeft;
        Node nodeRight;
        int wgt;

        public Edge(Node node1, Node node2, int weight){this.nodeLeft = node1;this.nodeRight = node2;this.wgt = weight;}
        @Override public int compareTo(Edge e){
            if(this.wgt < e.wgt){
                return -1;
            }
            else{
                return 1;
            }
        }
    }

    static class Node{
        public Node(){}
        Node parent;
        int num;

        int degree= 1;
        public Node(int num){this.num = num;}
        public boolean hasParent(){
            if(this.parent != null){
                return true;
            }
            else{
                return false;
            }
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