package Algorithm_Design;

import java.io.*;
import java.util.*;

public class Main42 {

    static Node[] nodes;
    static long hp;
    static long rst;


    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        hp = 0;
        rst = 0;
        int n = in.nextInt();
        nodes = new Node[n + 1];
        int i,x,y;
        long food,weight;
        for(i = 1;i <= n;i++){
            food = in.nextLong();
            nodes[i] = new Node(i,food);
        }
        for(i = 1;i < n;i++){
            nodes[x = in.nextInt()].adjacent.add(nodes[y = in.nextInt()]);
            nodes[y].adjacent.add(nodes[x]);
            nodes[x].map.put(y,weight = in.nextLong());
            nodes[y].map.put(x,weight);
        }
        nodes[1].travel = true;
        hp = nodes[1].food;
        dfs1(nodes[1]);
        nodes[1].travel = false;
        dfs2(nodes[1]);
        out.println(rst);
        out.close();
    }

    static long dfs1(Node node){
        int k;
        Node o;
        long ans = 0;
        ArrayList<Node> array = node.adjacent;
        int num = array.size();
        for(k = 0;k < num;k++){
            o = array.get(k);
            if(o.travel == false){
                o.investment = node.map.get(o.number);
                o.travel = true;
                //o.invest = node.weight.get(o.number - 1);
                o.income = o.food - o.investment + dfs1(o);
                o.p = o.income - o.investment;
                ans = ans + o.p;
            }
            else{
                continue;
            }
        }
        return ans;
    }

    static void dfs2(Node node){
        int ans = 0;
        Node o;
        ArrayList<Node> array1 = node.adjacent;
        array1.sort(Node::compareTo);
        while(!array1.isEmpty()){
            o = array1.get(0);
            ans = o.number;
            array1.remove(o);
            nodes[ans].adjacent.remove(node);
            hp = hp - node.map.get(ans);
            //hp = hp - node.weight.get(ans - 1);
            if(hp >= 0){
                hp  = hp + nodes[ans].food;
            }
            else{
                rst = rst - hp;
                hp = nodes[ans].food;
            }
            dfs2(nodes[ans]);
            hp = hp - node.map.get(ans);
            //hp = hp - node.weight.get(ans - 1);
            if(hp < 0){
                rst = rst - hp;
                hp = 0;
            }
        }
    }

    static class Node implements Comparable<Node>{
        int number;
        long food;
        Node(int number,long food){
            this.number = number;
            this.food = food;
            this.travel = false;
        }
        ArrayList<Node> adjacent = new ArrayList<>();
        HashMap<Integer,Long> map = new HashMap<>();
        long p;
        long income;
        long investment;
        boolean travel;

        @Override
        public int compareTo(Node n) {
            if(this.p >= 0 && n.p < 0){
                return -1;
            }
            else if(n.p >= 0 && this.p < 0){
                return 1;
            }
            else if(this.p < 0){
                if(n.income > this.income){
                    return 1;
                }
                else{
                    return -1;
                }
            }
            else{
                if(n.investment <= this.investment){
                    return 1;
                }
                else{
                    return -1;
                }
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