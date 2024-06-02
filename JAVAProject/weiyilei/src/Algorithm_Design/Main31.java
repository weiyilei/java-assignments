package Algorithm_Design;

import java.io.*;
import java.util.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Main31 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        Node[] nodes = new Node[n];
        Node[] rst = new Node[n];
        int i,a,b,loc = 0,num,k,temp;
        Node ru;
        for(i = 0;i < n;i++){
            nodes[i] = new Node(i + 1);
        }
        for(i = 1;i <= m;i++){
            a = in.nextInt();
            b = in.nextInt();
            nodes[a - 1].adjacent.add(nodes[b - 1]);
            nodes[b - 1].in_degree += 1;
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(n);
        for(i = 0;i < n;i++){
            if(nodes[i].in_degree == 0){
                queue.offer(i + 1);
            }
        }
        while(!queue.isEmpty()){
            temp = queue.poll();
            num = nodes[temp - 1].adjacent.size();
            for(k = 0;k < num;k++){
                ru = nodes[temp - 1].adjacent.get(k);
                ru.in_degree -= 1;
                if(ru.in_degree == 0){
                    queue.add(ru.num);
                }
            }
            rst[loc] = nodes[temp - 1];
            loc++;
        }
        if(loc == 0){
            out.println("impossible");
        }
        else{
            for(k = 0;k < n;k++){
                out.print(rst[k].num + " ");
            }
        }






        out.close();
    }

    static class Node{
        int num;
        Node(int num){
            this.num = num;
        }
        ArrayList<Node> adjacent = new ArrayList<>();
        int in_degree;
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