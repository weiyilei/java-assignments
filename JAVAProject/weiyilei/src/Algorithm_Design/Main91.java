package Algorithm_Design;

import java.io.*;
import java.lang.management.MemoryType;
import java.util.*;

public class Main91{
    public static long ans;
    public static long[][] memory = new long[60][2];
    /*public static long Left;
    public static long Right;
    public static long Sum;*/

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt(),a,b;
        long i,j;
        long[] is = new long[n];
        long[] js = new long[n];
        for(a = 0;a < 60;a++){
            for(b = 0;b < 2;b++){
                memory[a][b] = 1;
            }
        }
        for(a = 0;a < n;a++){
            i = in.nextLong();
            j = in.nextLong();
            is[a] = i;
            js[a] = j;
        }
        for(a = 0;a < 60;a++){
            if(a == 0){
                continue;
            }
            else{
                for(b = 0;b < 2;b++){
                    if(b == 0){
                        memory[a][b] = 2*memory[a - 1][b]+1;
                    }
                    else if(b == 1){
                        memory[a][b] = 2*memory[a - 1][b];
                    }
                }
            }
        }
        for(a = 0;a < n;a++){
            ans = 0;
            FindL(false,is[a],js[a],59);
            out.println(ans);
        }
        out.close();
    }

    public static void FindL(boolean judge,long l,long r,int amount){
        long Sum = memory[amount][0];
        long Left = memory[amount][1];
        long Right = Sum - Left;
        long act;
        act = l;
        long bord;
        bord = r;
        long x;
        x = Math.min(Left - 1,r);
        long y;
        y = Math.max(Left + 1,l);
        if(judge != false){
            if(bord == Sum && act == 1) ans = ans + Right;
            else{
                if(Left > act) FindL(true,act,x,amount - 1);
                if(Left < bord) FindL(false,2 * Left - bord,2 * Left - y,amount - 1);
            }
        }
        else {
            if(bord == Sum && act == 1) ans = ans + Left;
            else{
                if(Left > act) FindL(false,act,x,amount - 1);
                if(Left <= bord && Left >= act) ans = ans + 1;
                if(Left < bord) FindL(true,2 * Left - bord,2 * Left - y,amount - 1);
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