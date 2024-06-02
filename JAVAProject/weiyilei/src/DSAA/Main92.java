package DSAA;

import java.io.*;
import java.util.*;

public class Main92 {

    static class node{
        int deg = 0;
        ArrayList<node> next = new ArrayList<>();
        public node(int a){
            this.deg = a;
        }
        public node(){}
    }

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int m = in.nextInt();
        int i,a,b,temp,po;
        node[] list = new node[n];
        /*node92 cur;
        boolean judge = true;
        int[] array = new int[n + 1];
        Queue<Integer> queue = new LinkedList<>();*/
        for(i = 0;i < n;i++){
            list[i] = new node();
        }
        for(i = 0;i < m;i++) {
            a = in.nextInt();
            b = in.nextInt();
            list[a - 1].next.add(list[b - 1]);
            list[b - 1].next.add(list[a - 1]);
            list[a - 1].deg += 1;
            list[b - 1].deg += 1;
        }
        node[] stack = new node[n];
        temp = 0;
        for(i = 0;i < n;i++){
            if(list[i].deg <= 1){
                stack[temp] = list[i];
                temp += 1;
            }
        }
        po = 0;
        while(po != temp && temp != n){
            if(stack[po].deg == 1){
                node tmp = stack[po].next.get(0);
                tmp.deg -= 1;
                tmp.next.remove(stack[po]);
                if(tmp.deg == 1){
                    stack[temp] = tmp;
                    temp += 1;
                }
            }
            po += 1;
        }
        if(temp == n) {
            out.println("Good");
        }
        else {
            out.println("Bad");
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