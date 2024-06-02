package DSAA;

import java.io.*;
import java.util.*;

public class Main94 {

    static class node{
        int num;
        int dis = Integer.MAX_VALUE;
        node parent;
        ArrayList<node> son = new ArrayList<>();
        ArrayList<Integer> weight = new ArrayList<>();
        public node(int a){
            this.num = a;
        }
    }

    static class API{
        node[] list;
        int p = 0;

        public boolean isEmpty(){
            return p == 0;
        }

        public void insert(node a){
            list[++p] = a;
            swim(p);
        }

        private void swim(int a){
            while(a > 1 && list[a/2].dis > list[a].dis){
                exch(a/2,a);
                a = a/2;
            }
        }

        private void exch(int a,int b){
            node t = list[a];
            list[a] = list[b];
            list[b] = t;
        }

        public node dlt(){
            node m = list[1];
            exch(1,p--);
            list[p + 1] = null;
            sink(1);
            return m;
        }

        private void sink(int a){
            while(2*a <= p){
                int q = 2*a;
                if(q < p && list[q].dis > list[q + 1].dis){
                    q++;
                }
                if(list[a].dis <= list[q].dis){
                    break;
                }
                exch(a,q);
                a = q;
            }
        }

        public API(int a){
            this.list = new node[a + 1];
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        node[] list = new node[n];
        int i,a,b,w;
        for(i = 0;i < n;i++){
            list[i] = new node(i + 1);
        }
        for(i = 1;i <= m;i++){
            a = in.nextInt();
            b = in.nextInt();
            w = in.nextInt();
            list[a - 1].son.add(list[b - 1]);
            list[a - 1].weight.add(w);
        }
        list[0].dis = 0;

        API heap = new API(n);
        heap.insert(list[0]);
        while(!heap.isEmpty()){
            node parent = heap.dlt();
            for(i = 0;i < parent.son.size();i++){
                if(parent.dis + parent.weight.get(i) < parent.son.get(i).dis){
                    parent.son.get(i).dis = parent.dis + parent.weight.get(i);
                    parent.son.get(i).parent = parent;
                    heap.insert(parent.son.get(i));
                }
            }
        }
        if(list[n - 1].parent == null){
            out.println(-1);
        }
        else{
            out.println(list[n - 1].dis);
        }

        out.close();
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