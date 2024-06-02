package DSAA;

import java.io.*;
import java.util.*;

public class Main106 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n,m,p,k,i,j,u,v,w,s,t,n1,m1;
        n = in.nextInt();
        m = in.nextInt();
        p = in.nextInt();
        k = in.nextInt();
        long[][] d = new long[k + 1][n];
        Node[][] city = new Node[k + 1][n];
        Path te;
        Node uu,vv;
        long min;
        for(i = 0;i < k + 1;i++){
            for(j = 0;j < n;j++){
                city[i][j] = new Node(j + 1,i);
            }
        }
        for(i = 0;i < m;i++){
            u = in.nextInt();
            v = in.nextInt();
            w = in.nextInt();
            for(j = 0;j < k + 1;j++){
                te = new Path(w,city[j][u - 1],city[j][v - 1]);
                city[j][u - 1].out.add(te);
            }
        }
        for(i = 0;i < p;i++){
            u = in.nextInt();
            v = in.nextInt();
            for(j = 0;j < k + 1;j++){
                te = new Path(0,city[j][u - 1],city[j][v - 1]);
                city[j][u - 1].out.add(te);
            }
        }
        s = in.nextInt();
        t = in.nextInt();
        for(i = 0;i < k + 1;i++){
            for(j = 0;j < n;j++){
                d[i][j] = Long.MAX_VALUE;
            }
        }
        for(i = 0;i < k + 1;i++){
            city[i][s - 1].dis = 0;
            d[i][s - 1] = 0;
        }
        Comparator<Node> cmp = new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.dis > o2.dis) {
                    return 1;
                } else {
                    return -1;
                }
            }
        };
        Queue<Node> mh = new PriorityQueue<>(cmp);
        mh.add(city[0][s - 1]);
        while(!mh.isEmpty()){
            uu = mh.poll();
            for(i = 0;i < uu.out.size();i++){
                te = uu.out.get(i);
                if(te.weight == 0){
                    if(uu.active < k){
                        n1 = uu.active + 1;
                        m1 = te.end.val - 1;
                        vv = city[n1][m1];
                        if(vv.dis > uu.dis){
                            vv.dis = uu.dis;
                            d[n1][m1] = uu.dis;
                            mh.add(vv);
                        }
                    }
                }
                else{
                    vv = te.end;
                    if(vv.dis > uu.dis + te.weight){
                        vv.dis = uu.dis + te.weight;
                        d[vv.active][vv.val - 1] = uu.dis + te.weight;
                        mh.add(vv);
                    }
                }
            }
        }
        min = d[0][t - 1];
        for(i = 1;i < k + 1;i++){
            min = Math.min(min,d[i][t - 1]);
        }
        out.println(min);
        out.close();
    }

    static class Path{
        int weight;
        Node start;
        Node end;
        Path(int a,Node start,Node end){
            this.weight = a;
            this.start = start;
            this.end = end;
        }
    }

    static class Node{
        int val;
        int active;
        long dis = Long.MAX_VALUE;
        ArrayList<Path> out = new ArrayList<>();
        Node(int a,int b){
            this.val = a;
            this.active = b;
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