package DSAA;

import java.io.*;
import java.util.*;

public class Main47 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int t = in.nextInt();
        int i,n,k,j,num,loc,loc1,more,temp;
        long rst,l,r,max;
        int[] prev = new int[100100];
        int[] next = new int[100100];
        int[] position = new int[100100];
        for(i = 1;i <= t;i++){
            n = in.nextInt();
            k = in.nextInt();
            loc = 0;
            rst = 0;
            for(j = 1;j <= n;j++){
                num = in.nextInt();
                position[num] = j;
                next[loc] = num;
                prev[num] = loc;
                loc = num;
            }
            next[loc] = n + 1;
            position[n + 1] = n + 1;
            for(j = 1;j <= n;j++){
                loc = j;
                loc1 = j;
                more = 1;
                while(more < k && prev[loc] != 0){
                    loc = prev[loc];
                    more = more + 1;
                }
                while(more < k && next[loc1] != n + 1){
                    loc1 = next[loc1];
                    more = more + 1;
                }
                if(more < k){
                    continue;
                }
                for(;loc != next[j];loc = next[loc]){
                    l = (position[loc] - position[prev[loc]]) % (1000000007);
                    r = (position[next[loc1]] - position[loc1]) % (1000000007);
                    more = k;
                    temp = loc;
                    max = temp;
                    while(more > 1){
                        if(max < next[temp]){
                            max = next[temp];
                        }
                        more = more - 1;
                        temp = next[temp];
                    }
                    rst = (rst + (l*r*j*max) % (1000000007)) % (1000000007);
                    loc1 = next[loc1];
                    if(loc1 >= n + 1){
                        break;
                    }
                }
                prev[next[j]] = prev[j];
                next[prev[j]] = next[j];
            }
            out.println((rst) % (1000000007));
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