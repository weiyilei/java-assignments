package DSAA;

import java.io.*;
import java.util.*;

class Bag<Item> implements Iterable<Item>{
    private Node first;
    private int N;

    private class Node{
        Item item;
        Node next;
    }

    public void add(Item item){
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        N++;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return N;
    }

    @Override
    public Iterator<Item> iterator(){
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{
        private Node current = first;

        @Override
        public boolean hasNext(){
            return current != null;
        }

        @Override
        public Item next(){
            Item item = current.item;
            current = current.next;
            return item;
        }

        @Override
        public void remove(){}
    }
}

public class Main95 {

    static class graph {
        int V;
        int E;
        Bag<Integer>[] adj;

        public graph(int a){
            this.V = a;
            this.E = 0;
            adj = (Bag<Integer>[]) new Bag[a + 1];

            for(int z = 0;z < a + 1;z++){
                adj[z] = new Bag<Integer>();
            }
        }

        public int V(){
            return V;
        }

        public int E(){
            return E;
        }

        public void addEdge(int a,int b){
            adj[a].add(b);
            adj[b].add(a);
            E = E + 1;
        }

        public Iterable<Integer> adj(int a){
            return adj[a];
        }

        public void bfs(graph Graph,int start,int end,boolean[] mark){
            Queue<Integer> queue = new LinkedList<>();
            mark[start] = true;
            queue.add(start);
            while(!queue.isEmpty()){
                int t = queue.remove();
                for(int w:Graph.adj(t)){
                    if(!mark[w] && w != end){
                        mark[w] = true;
                        queue.add(w);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int a = in.nextInt();
        int b = in.nextInt();
        int i,p,q,ca,cb;
        boolean[] markA = new boolean[n + 1];
        boolean[] markB = new boolean[n + 1];
        for(i = 0;i < n + 1;i++){
            markA[i] = false;
            markB[i] = false;
        }
        graph Graph = new graph(n);
        for(i = 0;i < m;i++){
            p = in.nextInt();
            q = in.nextInt();
            Graph.addEdge(p,q);
        }
        Graph.bfs(Graph,a,b,markA);
        Graph.bfs(Graph,b,a,markB);
        markA[a] = false;
        markB[b] = false;
        ca = 0;
        cb = 0;
        for(i = 0;i < n + 1;i++){
            if(markA[i] && !markB[i]){
                ca = ca + 1;
            }
            if(!markA[i] && markB[i]){
                cb = cb + 1;
            }
        }
        out.println(ca*cb);
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