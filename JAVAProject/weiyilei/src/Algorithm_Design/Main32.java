package Algorithm_Design;

import java.io.*;
import java.util.*;

public class Main32 {

    public static Node[] nodes;
    public static long[] sum;
    public static long rst;
    public static long[] chain;

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);

        int n = in.nextInt();
        nodes = new Node[n + 1];
        int i,x,y;
        long wgt;
        for(i = 1;i <= n;i++){
            wgt = in.nextLong();
            nodes[i] = new Node(i,wgt);
        }
        for(i = 1;i <= n - 1;i++){
            x = in.nextInt();
            y = in.nextInt();
            nodes[x].adjacent.add(nodes[y]);
            nodes[y].adjacent.add(nodes[x]);
        }
        sum = new long[n + 1];
        chain = new long[n + 1];
        Sum(nodes[1],null,0);
        dfs(nodes[1],null);
        out.println(rst);

        out.close();
    }

    static class Node{
        int num;
        long wgt;
        Node(int num,long wgt){
            this.num = num;
            this.wgt = wgt;
        }
        Node(){}
        ArrayList<Node> adjacent = new ArrayList<>();
    }

    static void Sum(Node node,Node parent,int d){
        int k,number;
        Node temp;
        sum[node.num] = node.wgt;
        number = node.adjacent.size();
        for(k = 0;k < number;k++){
            temp = node.adjacent.get(k);
            if(! (temp == parent) ){
                Sum(temp,node,d + 1);
                sum[node.num] += sum[temp.num];
            }
        }
        chain[1] += node.wgt*d;
    }

    static void dfs(Node node,Node parent){
        int k,number;
        Node temp;
        if(node.num != 1){
            chain[node.num] = chain[parent.num] + sum[1] - sum[node.num] * 2;
            rst = chain[node.num] > rst ? chain[node.num] : rst;
        }
        number = node.adjacent.size();
        for(k = 0;k < number;k++){
            temp = node.adjacent.get(k);
            if( !(temp == parent) ){
                dfs(temp,node);
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