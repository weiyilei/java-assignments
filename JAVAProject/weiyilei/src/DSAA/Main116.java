package DSAA;

import java.io.*;
import java.util.*;

public class Main116 {

    public static void main(String[] args) throws IOException {
        Reader in=new Reader();
        PrintWriter out=new PrintWriter(System.out);
        int n = in.nextInt();
        int i,chosen,x,k,j,cur,temp,swap;
        int[] array = new int[3000000];
        cur = 1;
        for(i = 1;i <= n;i++){
            chosen = in.nextInt();
            if(chosen == 1){
                x = in.nextInt();
                array[cur] = x;
                temp = cur;
                while(temp != 1 && array[temp/2] < array[temp]){
                    swap = array[temp];
                    array[temp] = array[temp/2];
                    array[temp/2] = swap;
                    temp = temp/2;
                }
                cur++;
            }
            else{
                k = in.nextInt();
                for(j = 1;j < k;j++){
                    cur--;
                    array[1] = array[cur];
                    array[cur] = 0;
                    temp = 1;
                    while(array[temp] < array[2*temp] || array[temp] < array[2*temp + 1]){
                        if(array[2*temp] > array[2*temp + 1]){
                            swap = array[2*temp];
                            array[2*temp] = array[temp];
                            array[temp] = swap;
                            temp = 2*temp;
                        }
                        else{
                            swap = array[2*temp + 1];
                            array[2*temp + 1] = array[temp];
                            array[temp] = swap;
                            temp = 2*temp + 1;
                        }
                    }
                }
                out.println(array[1]);
            }
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