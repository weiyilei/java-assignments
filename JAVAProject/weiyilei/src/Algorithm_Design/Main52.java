package Algorithm_Design;

import java.io.*;
import java.util.*;


public class Main52 {

    static Node[] nodes;
    static Node first;
    static Node last;

    public static long Dijkstra(){
        edge exp;
        Node punch = first;
        long ans = 0;
        first.judge = true;
        PriorityQueue<edge> queue = new PriorityQueue<>(edge::compareTo);
        while(! last.equals(punch)){
            queue.addAll(punch.copy(ans));
            exp = queue.remove();
            punch = exp.next;
            while(true){
                if(punch.judge == false){
                    break;
                }
                else{
                    exp = queue.remove();
                    punch = exp.next;
                }
            }
            punch.judge = true;
            ans = exp.rst;
        }
        return ans;
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int i,x,y;
        long w,a,b,answer;
        nodes = new Node[n];
        edge e;
        for(i = 0;i < n;i++){
            nodes[i] = new Node();
        }
        for(i = 0;i < m;i++){
            x = in.nextInt();
            y = in.nextInt();
            w = in.nextLong();
            e = new edge(nodes[y - 1],w);
            nodes[x - 1].add(e);
        }
        for(i = 0;i < n;i++){
            a = in.nextLong();
            b = in.nextLong();
            nodes[i].a = a;
            nodes[i].b = b;
            nodes[i].sum = nodes[i].a + nodes[i].b;
        }
        first = nodes[0];
        last = nodes[n - 1];
        answer = Dijkstra();
        out.println(answer);
        out.close();
    }

    static class Node{
        boolean judge;
        long sum;
        long a;
        long b;
        ArrayList<edge> array = new ArrayList<>();
        Node(){judge = false;}

        public ArrayList<edge> copy(long l){
            edge some;
            long num;
            for(int i = 0;i < array.size();i++){
                some = array.get(i);
                num = array.get(i).wgt;
                num = num + l;
                num = num % array.get(i).next.sum;
                if(array.get(i).next.a <= num){
                    num = 0;
                }
                else if(array.get(i).next.b == 0){
                    l = Long.MAX_VALUE;
                }
                else{
                    num = array.get(i).next.a - num;
                }
                array.get(i).rst = l ;
                array.get(i).rst = array.get(i).rst + array.get(i).wgt + num;
            }
            return array;
        }
        public void add(edge e){
            array.add(e);
        }
    }

    static class edge implements Comparable<edge>{
        long rst;
        long wgt;
        Node next;
        edge(Node n,long w){this.next = n;this.wgt = w; }
        @Override public int compareTo(edge e){
            if(this.rst < e.rst){
                return -1;
            }
            else{
                return 1;
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